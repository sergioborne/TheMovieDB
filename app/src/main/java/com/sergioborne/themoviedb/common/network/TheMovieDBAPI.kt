package com.sergioborne.themoviedb.common.network

import com.sergioborne.themoviedb.moviedetails.data.MovieDetails
import com.sergioborne.tmdbkotlinchallenge.data.Page
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDBAPI {

  @GET("discover/movie")
  fun getMovies(): Observable<Page>

  @GET("movie/{movieId}")
  fun getMovieDetails(@Path("movieId") movieId: Int): Observable<MovieDetails>

  @GET("movie/{movieId}/similar")
  fun getSimilarMovies(@Path("movieId") movieId: Int): Observable<Page>
}