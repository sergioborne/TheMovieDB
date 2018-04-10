package com.sergioborne.themoviedb.tvshowdetails.network

import com.sergioborne.themoviedb.common.network.TheMovieDBAPI
import com.sergioborne.themoviedb.tvshowdetails.data.TvShowDetails
import com.sergioborne.tmdbkotlinchallenge.data.Page
import io.reactivex.Observable

class TvShowDetailsServiceImpl(private val theMovieDBAPI: TheMovieDBAPI) : TvShowDetailsService {
  override fun getTvShowDetails(tvShowId: Int): Observable<TvShowDetails> {
    return theMovieDBAPI.getTvShowDetails(tvShowId).flatMap { response ->
      Observable.just(response)
    }
  }

  override fun getSimilarTvShows(tvShowId: Int): Observable<Page> {
    return theMovieDBAPI.getSimilarTvShows(tvShowId).flatMap { response ->
      Observable.just(response)
    }
  }
}
