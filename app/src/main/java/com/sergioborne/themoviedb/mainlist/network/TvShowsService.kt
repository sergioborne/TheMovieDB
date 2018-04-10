package com.sergioborne.themoviedb.mainlist.network

import com.sergioborne.tmdbkotlinchallenge.data.Page
import io.reactivex.Observable

interface TvShowsService {

  fun getPopularTvShows(page: Int): Observable<Page>

}