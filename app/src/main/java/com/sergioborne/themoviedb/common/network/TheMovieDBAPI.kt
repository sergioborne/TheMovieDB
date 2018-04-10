package com.sergioborne.themoviedb.common.network

import com.sergioborne.themoviedb.tvshowdetails.data.TvShowDetails
import com.sergioborne.tmdbkotlinchallenge.data.Page
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBAPI {

  @GET("tv/popular")
  fun getTvShows(@Query("page") page: Int): Observable<Page>

  @GET("tv/{tvShowId}")
  fun getTvShowDetails(@Path("tvShowId") tvShowId: Int): Observable<TvShowDetails>

  @GET("tv/{tvShowId}/similar")
  fun getSimilarTvShows(@Path("tvShowId") tvShowId: Int): Observable<Page>
}