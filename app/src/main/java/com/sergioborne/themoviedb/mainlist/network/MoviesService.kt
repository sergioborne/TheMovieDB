package com.sergioborne.themoviedb.mainlist.network

import com.sergioborne.tmdbkotlinchallenge.data.Movie
import io.reactivex.Observable

interface MoviesService {

  fun getPopularMovies(): Observable<List<Movie>>

}