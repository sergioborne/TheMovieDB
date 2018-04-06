package com.sergioborne.themoviedb.mainlist.network

import com.sergioborne.themoviedb.common.network.TheMovieDBAPI
import com.sergioborne.tmdbkotlinchallenge.data.Movie
import io.reactivex.Observable

class MoviesServiceImpl(var theMovieDBAPI: TheMovieDBAPI) : MoviesService {

  override fun getPopularMovies(): Observable<List<Movie>> {
    return theMovieDBAPI.getMovies().flatMap { response -> Observable.just(response.results) }
  }
}