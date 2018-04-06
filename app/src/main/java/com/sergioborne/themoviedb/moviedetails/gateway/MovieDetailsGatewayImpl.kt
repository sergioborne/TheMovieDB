package com.sergioborne.themoviedb.moviedetails.gateway

import com.sergioborne.themoviedb.common.OutcomeListener
import com.sergioborne.themoviedb.common.SchedulerProvider
import com.sergioborne.themoviedb.moviedetails.data.MovieDetails
import com.sergioborne.themoviedb.moviedetails.network.MovieDetailsService
import com.sergioborne.tmdbkotlinchallenge.data.Movie

class MovieDetailsGatewayImpl(private val schedulerProvider: SchedulerProvider,
    private val service: MovieDetailsService) : MovieDetailsGateway {

  override fun loadMovieDetails(movieId: Int, listener: OutcomeListener<MovieDetails>) {
    service.getMovieDetails(movieId).subscribeOn(
        schedulerProvider.backgroundThreadScheduler()).observeOn(
        schedulerProvider.mainThreadScheduler()).subscribe(listener::success, listener::error)
  }

  override fun loadSimilarMovies(movieId: Int, listener: OutcomeListener<List<Movie>>) {
    service.getSimilarMovies(movieId).subscribeOn(
        schedulerProvider.backgroundThreadScheduler()).observeOn(
        schedulerProvider.mainThreadScheduler()).subscribe(listener::success, listener::error)
  }
}