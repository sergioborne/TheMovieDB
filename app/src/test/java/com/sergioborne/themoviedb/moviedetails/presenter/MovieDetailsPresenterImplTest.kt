package com.sergioborne.themoviedb.moviedetails.presenter

import com.sergioborne.themoviedb.R
import com.sergioborne.themoviedb.common.OutcomeListener
import com.sergioborne.themoviedb.moviedetails.data.MovieDetails
import com.sergioborne.themoviedb.moviedetails.gateway.MovieDetailsGateway
import com.sergioborne.themoviedb.moviedetails.ui.MovieDetailsView
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
class MovieDetailsPresenterImplTest {

  @Mock
  lateinit var gateway: MovieDetailsGateway
  @Mock
  lateinit var view: MovieDetailsView
  @Captor
  lateinit var outcomeListenerDetailsArgumentCaptor: ArgumentCaptor<OutcomeListener<MovieDetails>>
  @Captor
  lateinit var outcomeListenerPageArgumentCaptor: ArgumentCaptor<OutcomeListener<Page>>

  lateinit var presenter: MovieDetailsPresenterImpl

  private val movieId = 123

  @Before
  fun setUp() {
    presenter = MovieDetailsPresenterImpl(view, gateway)
  }

  @Test
  fun init() {
    presenter.init(movieId)
    verify(gateway).loadMovieDetails(eq(movieId), any())
    verify(gateway).loadSimilarMovies(eq(movieId), any())
  }

  @Test
  fun `view is updated when load movies is success`() {
    val movieDetails: MovieDetails = mock()
    presenter.init(movieId)
    verify(gateway).loadMovieDetails(eq(movieId), capture(outcomeListenerDetailsArgumentCaptor))
    outcomeListenerDetailsArgumentCaptor.value.success(movieDetails)
    verify(view).showMovieDetails(anyString())
  }

  @Test
  fun `view is updated when similar movies is success`() {
    val page: Page = mock()
    presenter.init(movieId)
    verify(gateway).loadSimilarMovies(eq(movieId), capture(outcomeListenerPageArgumentCaptor))
    outcomeListenerPageArgumentCaptor.value.success(page)
    verify(view).showSimilarMovies(anyList())
  }

  @Test
  fun `view shows error when load movies is not success`() {
    presenter.init(movieId)
    verify(gateway).loadMovieDetails(eq(movieId), capture(outcomeListenerDetailsArgumentCaptor))
    outcomeListenerDetailsArgumentCaptor.value.error(Throwable())
    verify(view).showError(R.string.default_error_message)
  }

  @Test
  fun `view shows error when similar movies is not success`() {
    presenter.init(movieId)
    verify(gateway).loadSimilarMovies(eq(movieId), capture(outcomeListenerPageArgumentCaptor))
    outcomeListenerPageArgumentCaptor.value.error(Throwable())
    verify(view).showError(R.string.default_error_message)
  }
}