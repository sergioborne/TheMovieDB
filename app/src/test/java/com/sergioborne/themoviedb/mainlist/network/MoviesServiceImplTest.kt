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
class MoviesServiceImplTest {

  @Mock
  lateinit var api: TheMovieDBAPI

  private lateinit var service: MoviesServiceImpl

  @Before
  fun setUp() {
    service = MoviesServiceImpl(api)
  }

  @Test
  fun getPopularMovies() {
    val page: Page = mock()
    `when`(api.getMovies(ArgumentMatchers.anyInt())).thenReturn(Observable.just(page))
    service.getPopularMovies(1)
    verify(api).getMovieDetails(1)
  }
}