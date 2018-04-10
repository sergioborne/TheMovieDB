package com.sergioborne.themoviedb.mainlist.gateway

import com.sergioborne.themoviedb.common.OutcomeListener
import com.sergioborne.themoviedb.mainlist.network.TvShowsService
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
  private lateinit var service: TvShowsService
  @Mock
  private lateinit var tvShowDetailsOutcomeListener: OutcomeListener<Page>

  private lateinit var gateway: MainListGatewayImpl

  @Before
  fun setUp() {
    gateway = MainListGatewayImpl(TestSchedulerProvider(), service)
  }


  @Test
  fun `when load tv shows is success listener success method is called`() {
    val page: Page = mock()
    `when`(service.getPopularTvShows(1)).thenReturn(Observable.just(page))
    gateway.loadTvShows(1, tvShowDetailsOutcomeListener)
    verify(tvShowDetailsOutcomeListener).success(page)
  }

  @Test
  fun `when load tv shows return error listener error method is called`() {
    val error: Throwable = mock()
    `when`(service.getPopularTvShows(1)).thenReturn(Observable.error(error))
    gateway.loadTvShows(1, tvShowDetailsOutcomeListener)
    verify(tvShowDetailsOutcomeListener).error(error)
  }
}