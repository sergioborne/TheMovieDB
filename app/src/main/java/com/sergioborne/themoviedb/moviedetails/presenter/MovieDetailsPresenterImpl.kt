package com.sergioborne.themoviedb.moviedetails.presenter

import com.sergioborne.themoviedb.R
import com.sergioborne.themoviedb.common.OutcomeListener
import com.sergioborne.themoviedb.moviedetails.data.MovieDetails
import com.sergioborne.themoviedb.moviedetails.gateway.MovieDetailsGateway
import com.sergioborne.themoviedb.moviedetails.ui.MovieDetailsView
import com.sergioborne.themoviedb.moviedetails.ui.SimilarMovieViewModel
import com.sergioborne.tmdbkotlinchallenge.data.Movie

class MovieDetailsPresenterImpl(private val movieDetailsView: MovieDetailsView,
    private val gateway: MovieDetailsGateway) : MovieDetailsPresenter {

  override fun init(movieId: Int) {
    updateDetails(movieId)
  }

  private fun updateDetails(movieId: Int) {
    loadMovieDetails(movieId)
    loadSimilarMovies(movieId)
  }

  private fun loadMovieDetails(movieId: Int) {
    gateway.loadMovieDetails(movieId, object : OutcomeListener<MovieDetails> {
      override fun success(movieDetails: MovieDetails) {
        movieDetailsView.showMovieDetails(movieDetails.overview ?: "")
      }

      override fun error(error: Throwable) {
        movieDetailsView.showError(R.string.default_error_message)
      }
    })
  }

  private fun loadSimilarMovies(movieId: Int) {
    gateway.loadSimilarMovies(movieId, object : OutcomeListener<List<Movie>> {
      override fun success(similarMovies: List<Movie>) {
        movieDetailsView.showSimilarMovies(createViewModelsList(similarMovies))
      }

      override fun error(error: Throwable) {
        movieDetailsView.showError(R.string.default_error_message)
      }
    })
  }

  private fun createViewModelsList(moviesList: List<Movie>): List<SimilarMovieViewModel> {
    return moviesList.map { this.createViewModelFromMovie(it) }
  }

  private fun createViewModelFromMovie(movie: Movie): SimilarMovieViewModel {
    return SimilarMovieViewModel(movie.title, "https://image.tmdb.org/t/p/w500" + movie.poster_path)
  }
}