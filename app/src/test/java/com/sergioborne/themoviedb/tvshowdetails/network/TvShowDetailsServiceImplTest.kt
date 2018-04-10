package com.sergioborne.themoviedb.tvshowdetails.network

import com.sergioborne.themoviedb.common.network.TheMovieDBAPI
import com.sergioborne.themoviedb.tvshowdetails.data.TvShowDetails
import com.sergioborne.themoviedb.utils.mock
import com.sergioborne.tmdbkotlinchallenge.data.Page
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowDetailsServiceImplTest {

  @Mock
  private lateinit var api: TheMovieDBAPI

  private lateinit var tvShowDetailsService: TvShowDetailsServiceImpl

  private val tvShowId = 123

  @Before
  fun setUp() {
    tvShowDetailsService = TvShowDetailsServiceImpl(api)
  }

  @Test
  fun getMovieDetails() {
    val tvShowDetails: TvShowDetails = mock()
    `when`(api.getTvShowDetails(anyInt())).thenReturn(Observable.just(tvShowDetails))
    tvShowDetailsService.getTvShowDetails(tvShowId)
    verify(api).getTvShowDetails(tvShowId)
  }

  @Test
  fun getSimilarMovies() {
    val page: Page = mock()
    `when`(api.getSimilarTvShows(anyInt())).thenReturn(Observable.just(page))
    tvShowDetailsService.getSimilarTvShows(tvShowId)
    verify(api).getSimilarTvShows(tvShowId)
  }
}