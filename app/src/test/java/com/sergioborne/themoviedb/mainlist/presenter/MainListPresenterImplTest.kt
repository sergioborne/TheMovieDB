package com.sergioborne.themoviedb.mainlist.presenter

import com.sergioborne.themoviedb.R
import com.sergioborne.themoviedb.common.OutcomeListener
import com.sergioborne.themoviedb.mainlist.gateway.MainListGateway
import com.sergioborne.themoviedb.mainlist.ui.MainListView
import com.sergioborne.themoviedb.utils.any
import com.sergioborne.themoviedb.utils.capture
import com.sergioborne.themoviedb.utils.eq
import com.sergioborne.themoviedb.utils.mock
import com.sergioborne.tmdbkotlinchallenge.data.Page
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainListPresenterImplTest {

  @Mock
  private lateinit var view: MainListView
  @Mock
  private lateinit var gateway: MainListGateway
  @Captor
  private lateinit var outcomeListenerPageArgumentCaptor: ArgumentCaptor<OutcomeListener<Page>>

  @Mock
  private lateinit var page: Page

  private lateinit var presenter: MainListPresenterImpl

  @Before
  fun setUp() {
    presenter = MainListPresenterImpl(view, gateway)
  }

  @Test
  fun init() {
    presenter.init()
    verify(view).showLoadingIndicator()
    verifyLoadFirstPage()
  }

  @Test
  fun refreshList() {
    presenter.refreshList()
    verifyLoadFirstPage()
  }

  private fun verifyLoadFirstPage() {
    verify(gateway).loadMovies(anyInt(), any())
    verify(view).clearMovieList()
  }

  @Test
  fun `load movies success`() {
    presenter.init()
    verify(gateway).loadMovies(anyInt(), capture(outcomeListenerPageArgumentCaptor))
    outcomeListenerPageArgumentCaptor.value.success(page)
    verify(view).updateMoviesList(anyList())
    verify(view).hideLoadingIndicator()
    assertFalse(presenter.isFetchingData)
  }

  @Test
  fun `load movies error`() {
    val error: Throwable = mock()
    presenter.init()
    verify(gateway).loadMovies(anyInt(), capture(outcomeListenerPageArgumentCaptor))
    outcomeListenerPageArgumentCaptor.value.error(error)
    verify(view).showError(R.string.default_error_message)
    verify(view).hideLoadingIndicator()
    assertFalse(presenter.isFetchingData)
  }

  @Test
  fun `when bottom of the list is reached and there are more pages, next page is loaded`() {
    presenter.isFetchingData = false
    presenter.isThereMorePages = true
    presenter.currentPage = 1

    presenter.bottomListReached()

    verify(gateway).loadMovies(eq(2), any())
    assertTrue(presenter.isFetchingData)
  }

  @Test
  fun `when bottom of the list is reached and there are no more pages, nothing happens`() {
    presenter.isFetchingData = false
    presenter.isThereMorePages = false

    presenter.bottomListReached()

    verify(gateway, never()).loadMovies(anyInt(), any())
    assertFalse(presenter.isFetchingData)
  }

  @Test
  fun `when bottom of the list is reached and is already loading something, nothing happens`() {
    presenter.isFetchingData = true
    presenter.isThereMorePages = true

    presenter.bottomListReached()

    verify(gateway, never()).loadMovies(anyInt(), any())
  }

  @Test
  fun `isThereMorePages is true when there is at least 1 more page`() {
    `when`(page.total_pages).thenReturn(3)
    presenter.currentPage = 1
    presenter.bottomListReached()
    verify(gateway).loadMovies(anyInt(), capture(outcomeListenerPageArgumentCaptor))
    outcomeListenerPageArgumentCaptor.value.success(page)
    assertTrue(presenter.isThereMorePages)
  }

  @Test
  fun `isThereMorePages is false if it's the last page`() {
    `when`(page.total_pages).thenReturn(3)
    presenter.currentPage = 3
    presenter.bottomListReached()
    verify(gateway).loadMovies(anyInt(), capture(outcomeListenerPageArgumentCaptor))
    outcomeListenerPageArgumentCaptor.value.success(page)
    assertFalse(presenter.isThereMorePages)
  }
}