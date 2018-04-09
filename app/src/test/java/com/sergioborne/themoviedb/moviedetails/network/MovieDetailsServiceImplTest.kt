package com.sergioborne.themoviedb.moviedetails.network

import com.sergioborne.themoviedb.common.network.TheMovieDBAPI
import com.sergioborne.themoviedb.moviedetails.data.MovieDetails
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
class MovieDetailsServiceImplTest {

  @Mock
  lateinit var api: TheMovieDBAPI

  private lateinit var movieDetailsService: MovieDetailsServiceImpl

  private val movieId = 123

  @Before
  fun setUp() {
    movieDetailsService = MovieDetailsServiceImpl(api)
  }

  @Test
  fun getMovieDetails() {
    val movieDetails: MovieDetails = mock()
    `when`(api.getMovieDetails(anyInt())).thenReturn(Observable.just(movieDetails))
    movieDetailsService.getMovieDetails(movieId)
    verify(api).getMovieDetails(movieId)
  }

  @Test
  fun getSimilarMovies() {
    val page: Page = mock()
    `when`(api.getSimilarMovies(anyInt())).thenReturn(Observable.just(page))
    movieDetailsService.getSimilarMovies(movieId)
    verify(api).getSimilarMovies(movieId)
  }
}