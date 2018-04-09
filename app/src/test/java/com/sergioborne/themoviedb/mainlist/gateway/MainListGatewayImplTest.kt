package com.sergioborne.themoviedb.mainlist.gateway

import com.sergioborne.themoviedb.common.OutcomeListener
import com.sergioborne.themoviedb.mainlist.network.MoviesService
import com.sergioborne.themoviedb.utils.TestSchedulerProvider
import com.sergioborne.themoviedb.utils.mock
import com.sergioborne.tmdbkotlinchallenge.data.Page
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainListGatewayImplTest {

  @Mock
  lateinit var service: MoviesService
  @Mock
  lateinit var movieDetailsOutcomeListener: OutcomeListener<Page>

  private lateinit var gateway: MainListGatewayImpl

  @Before
  fun setUp() {
    gateway = MainListGatewayImpl(TestSchedulerProvider(), service)
  }


  @Test
  fun `when load movies is success listener success method is called`() {
    val page: Page = mock()
    `when`(service.getPopularMovies(1)).thenReturn(Observable.just(page))
    gateway.loadMovies(1, movieDetailsOutcomeListener)
    verify(movieDetailsOutcomeListener).success(page)
  }

  @Test
  fun `when load movies return error listener error method is called`() {
    val error: Throwable = mock()
    `when`(service.getPopularMovies(1)).thenReturn(Observable.error(error))
    gateway.loadMovies(1, movieDetailsOutcomeListener)
    verify(movieDetailsOutcomeListener).error(error)
  }
}