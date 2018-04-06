package com.sergioborne.themoviedb.moviedetails.network

import com.sergioborne.themoviedb.common.TheMovieDBAPI
import com.sergioborne.themoviedb.moviedetails.data.MovieDetails
import com.sergioborne.tmdbkotlinchallenge.data.Movie
import io.reactivex.Observable

class MovieDetailsServiceImpl(private val theMovieDBAPI: TheMovieDBAPI) : MovieDetailsService {
  override fun getMovieDetails(movieId: Int): Observable<MovieDetails> {
    return theMovieDBAPI.getMovieDetails(movieId).flatMap { response ->
      Observable.just(response)
    }
  }

  override fun getSimilarMovies(movieId: Int): Observable<List<Movie>> {
    return theMovieDBAPI.getSimilarMovies(movieId).flatMap { response ->
      Observable.just(response.results)
    }
  }
}
