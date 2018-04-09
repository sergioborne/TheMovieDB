package com.sergioborne.themoviedb.moviedetails.gateway

import com.sergioborne.themoviedb.common.OutcomeListener
import com.sergioborne.themoviedb.moviedetails.data.MovieDetails
import com.sergioborne.themoviedb.moviedetails.network.MovieDetailsService
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
class MovieDetailsGatewayImplTest {

  @Mock
  private lateinit var service: MovieDetailsService
  @Mock
  private lateinit var movieDetailsOutcomeListener: OutcomeListener<MovieDetails>
  @Mock
  private lateinit var similarMoviesOutcomeListener: OutcomeListener<Page>

  private lateinit var gateway: MovieDetailsGatewayImpl

  private val movieId = 123

  @Before
  fun setUp() {
    gateway = MovieDetailsGatewayImpl(TestSchedulerProvider(), service)
  }

  @Test
  fun `when load movie is success listener success method is called`() {
    val movieDetails = MovieDetails()
    `when`(service.getMovieDetails(movieId)).thenReturn(Observable.just(movieDetails))
    gateway.loadMovieDetails(movieId, movieDetailsOutcomeListener)
    verify(movieDetailsOutcomeListener).success(movieDetails)
  }

  @Test
  fun `when load movie is error listener error method is called`() {
    val error: Throwable = mock()
    `when`(service.getMovieDetails(movieId)).thenReturn(Observable.error(error))
    gateway.loadMovieDetails(movieId, movieDetailsOutcomeListener)
    verify(movieDetailsOutcomeListener).error(error)
  }

  @Test
  fun `when similar movies is success listener success method is called`() {
    val page: Page = mock()
    `when`(service.getSimilarMovies(movieId)).thenReturn(Observable.just(page))
    gateway.loadSimilarMovies(movieId, similarMoviesOutcomeListener)
    verify(similarMoviesOutcomeListener).success(page)
  }

  @Test
  fun `when similar movies return error listener error method is called`() {
    val error: Throwable = mock()
    `when`(service.getSimilarMovies(movieId)).thenReturn(Observable.error(error))
    gateway.loadSimilarMovies(movieId, similarMoviesOutcomeListener)
    verify(similarMoviesOutcomeListener).error(error)
  }
}