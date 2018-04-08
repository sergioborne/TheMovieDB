package com.sergioborne.themoviedb.mainlist.network

import com.sergioborne.themoviedb.common.network.TheMovieDBAPI
import com.sergioborne.tmdbkotlinchallenge.data.Page
import io.reactivex.Observable

class MoviesServiceImpl(var theMovieDBAPI: TheMovieDBAPI) : MoviesService {

  override fun getPopularMovies(page: Int): Observable<Page> {
    return theMovieDBAPI.getMovies(page).flatMap { response -> Observable.just(response) }
  }
}