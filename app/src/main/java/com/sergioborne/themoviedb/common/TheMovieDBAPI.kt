package com.sergioborne.themoviedb.common

import com.sergioborne.tmdbkotlinchallenge.data.Page
import io.reactivex.Observable
import retrofit2.http.GET

interface TheMovieDBAPI {

  @GET("discover/movie")
  fun getMovies(): Observable<Page>
}