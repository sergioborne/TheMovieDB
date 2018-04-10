package com.sergioborne.themoviedb.tvshowdetails.network

import com.sergioborne.themoviedb.tvshowdetails.data.TvShowDetails
import com.sergioborne.tmdbkotlinchallenge.data.Page
import io.reactivex.Observable

interface TvShowDetailsService {

  fun getTvShowDetails(tvShowId: Int): Observable<TvShowDetails>

  fun getSimilarTvShows(tvShowId: Int): Observable<Page>
}