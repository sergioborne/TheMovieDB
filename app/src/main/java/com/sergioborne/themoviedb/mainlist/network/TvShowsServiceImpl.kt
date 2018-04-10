package com.sergioborne.themoviedb.mainlist.network

import com.sergioborne.themoviedb.common.network.TheMovieDBAPI
import com.sergioborne.tmdbkotlinchallenge.data.Page
import io.reactivex.Observable

class TvShowsServiceImpl(var theMovieDBAPI: TheMovieDBAPI) : TvShowsService {

  override fun getPopularTvShows(page: Int): Observable<Page> {
    return theMovieDBAPI.getTvShows(page).flatMap { response -> Observable.just(response) }
  }
}