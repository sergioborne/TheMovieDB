package com.sergioborne.themoviedb.moviedetails.presenter

import com.sergioborne.themoviedb.common.OutcomeListener
import com.sergioborne.themoviedb.moviedetails.data.MovieDetails
import com.sergioborne.themoviedb.moviedetails.gateway.MovieDetailsGateway
import com.sergioborne.themoviedb.moviedetails.ui.MovieDetailsView
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

      }
    })
  }

  private fun loadSimilarMovies(movieId: Int) {
    gateway.loadSimilarMovies(movieId, object : OutcomeListener<List<Movie>> {
      override fun success(movieDetails: List<Movie>) {

      }

      override fun error(error: Throwable) {

      }
    })
  }
}