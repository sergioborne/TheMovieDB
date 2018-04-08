package com.sergioborne.themoviedb.moviedetails.network

import com.sergioborne.themoviedb.moviedetails.data.MovieDetails
import com.sergioborne.tmdbkotlinchallenge.data.Page
import io.reactivex.Observable

interface MovieDetailsService {

  fun getMovieDetails(movieId: Int): Observable<MovieDetails>

  fun getSimilarMovies(movieId: Int): Observable<Page>
}