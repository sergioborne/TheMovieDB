package com.sergioborne.themoviedb.mainlist.network

import com.sergioborne.tmdbkotlinchallenge.data.Page
import io.reactivex.Observable

interface MoviesService {

  fun getPopularMovies(page: Int): Observable<Page>

}