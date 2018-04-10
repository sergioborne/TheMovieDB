package com.sergioborne.themoviedb.tvshowdetails.gateway

import com.sergioborne.themoviedb.common.OutcomeListener
import com.sergioborne.themoviedb.tvshowdetails.data.TvShowDetails
import com.sergioborne.themoviedb.tvshowdetails.network.TvShowDetailsService
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
class TvShowDetailsGatewayImplTest {

  @Mock
  private lateinit var service: TvShowDetailsService
  @Mock
  private lateinit var tvShowDetailsOutcomeListener: OutcomeListener<TvShowDetails>
  @Mock
  private lateinit var similarTvShowsOutcomeListener: OutcomeListener<Page>

  private lateinit var gateway: TvShowDetailsGatewayImpl

  private val tvShowId = 123

  @Before
  fun setUp() {
    gateway = TvShowDetailsGatewayImpl(TestSchedulerProvider(), service)
  }

  @Test
  fun `when load tv show is success listener success method is called`() {
    val movieDetails = TvShowDetails()
    `when`(service.getTvShowDetails(tvShowId)).thenReturn(Observable.just(movieDetails))
    gateway.loadTvShowDetails(tvShowId, tvShowDetailsOutcomeListener)
    verify(tvShowDetailsOutcomeListener).success(movieDetails)
  }

  @Test
  fun `when load tv show is error listener error method is called`() {
    val error: Throwable = mock()
    `when`(service.getTvShowDetails(tvShowId)).thenReturn(Observable.error(error))
    gateway.loadTvShowDetails(tvShowId, tvShowDetailsOutcomeListener)
    verify(tvShowDetailsOutcomeListener).error(error)
  }

  @Test
  fun `when similar tv shows is success listener success method is called`() {
    val page: Page = mock()
    `when`(service.getSimilarTvShows(tvShowId)).thenReturn(Observable.just(page))
    gateway.loadSimilarTvShows(tvShowId, similarTvShowsOutcomeListener)
    verify(similarTvShowsOutcomeListener).success(page)
  }

  @Test
  fun `when similar tv shows return error listener error method is called`() {
    val error: Throwable = mock()
    `when`(service.getSimilarTvShows(tvShowId)).thenReturn(Observable.error(error))
    gateway.loadSimilarTvShows(tvShowId, similarTvShowsOutcomeListener)
    verify(similarTvShowsOutcomeListener).error(error)
  }
}