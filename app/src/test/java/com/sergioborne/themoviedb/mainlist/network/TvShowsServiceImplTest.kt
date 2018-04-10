package com.sergioborne.themoviedb.mainlist.network

import com.sergioborne.themoviedb.common.network.TheMovieDBAPI
import com.sergioborne.themoviedb.utils.mock
import com.sergioborne.tmdbkotlinchallenge.data.Page
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowsServiceImplTest {

  @Mock
  private lateinit var api: TheMovieDBAPI

  private lateinit var service: TvShowsServiceImpl

  @Before
  fun setUp() {
    service = TvShowsServiceImpl(api)
  }

  @Test
  fun getPopularTvShows() {
    val page: Page = mock()
    `when`(api.getTvShows(ArgumentMatchers.anyInt())).thenReturn(Observable.just(page))
    service.getPopularTvShows(1)
    verify(api).getTvShows(1)
  }
}