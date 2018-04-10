package com.sergioborne.themoviedb.tvshowdetails.presenter

import com.sergioborne.themoviedb.R
import com.sergioborne.themoviedb.common.OutcomeListener
import com.sergioborne.themoviedb.tvshowdetails.data.TvShowDetails
import com.sergioborne.themoviedb.tvshowdetails.gateway.TvShowDetailsGateway
import com.sergioborne.themoviedb.tvshowdetails.ui.TvShowsDetailsView
import com.sergioborne.themoviedb.utils.any
import com.sergioborne.themoviedb.utils.capture
import com.sergioborne.themoviedb.utils.eq
import com.sergioborne.themoviedb.utils.mock
import com.sergioborne.tmdbkotlinchallenge.data.Page
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TvShowDetailsPresenterImplTest {

  @Mock
  private lateinit var gateway: TvShowDetailsGateway
  @Mock
  private lateinit var view: TvShowsDetailsView
  @Captor
  private lateinit var outcomeListenerDetailsArgumentCaptor: ArgumentCaptor<OutcomeListener<TvShowDetails>>
  @Captor
  private lateinit var outcomeListenerPageArgumentCaptor: ArgumentCaptor<OutcomeListener<Page>>

  lateinit var presenter: TvShowDetailsPresenterImpl

  private val tvShowId = 123

  @Before
  fun setUp() {
    presenter = TvShowDetailsPresenterImpl(view, gateway)
  }

  @Test
  fun init() {
    presenter.init(tvShowId)
    verify(gateway).loadTvShowDetails(eq(tvShowId), any())
    verify(gateway).loadSimilarTvShows(eq(tvShowId), any())
  }

  @Test
  fun `view is updated when load tv shows is success`() {
    val tvShowDetails: TvShowDetails = mock()
    presenter.init(tvShowId)
    verify(gateway).loadTvShowDetails(eq(tvShowId), capture(outcomeListenerDetailsArgumentCaptor))
    outcomeListenerDetailsArgumentCaptor.value.success(tvShowDetails)
    verify(view).showTvShowDetails(anyString())
  }

  @Test
  fun `view is updated when similar tv shows is success`() {
    val page: Page = mock()
    presenter.init(tvShowId)
    verify(gateway).loadSimilarTvShows(eq(tvShowId), capture(outcomeListenerPageArgumentCaptor))
    outcomeListenerPageArgumentCaptor.value.success(page)
    verify(view).showSimilarTvShows(anyList())
  }

  @Test
  fun `view shows error when load tv show is not success`() {
    presenter.init(tvShowId)
    verify(gateway).loadTvShowDetails(eq(tvShowId), capture(outcomeListenerDetailsArgumentCaptor))
    outcomeListenerDetailsArgumentCaptor.value.error(Throwable())
    verify(view).showError(R.string.default_error_message)
  }

  @Test
  fun `view shows error when similar tv shows is not success`() {
    presenter.init(tvShowId)
    verify(gateway).loadSimilarTvShows(eq(tvShowId), capture(outcomeListenerPageArgumentCaptor))
    outcomeListenerPageArgumentCaptor.value.error(Throwable())
    verify(view).showError(R.string.default_error_message)
  }
}