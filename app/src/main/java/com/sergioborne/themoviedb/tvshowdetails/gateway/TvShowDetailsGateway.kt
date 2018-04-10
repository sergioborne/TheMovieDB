package com.sergioborne.themoviedb.tvshowdetails.gateway

import com.sergioborne.themoviedb.common.OutcomeListener
import com.sergioborne.themoviedb.tvshowdetails.data.TvShowDetails
import com.sergioborne.tmdbkotlinchallenge.data.Page

interface TvShowDetailsGateway {

  fun loadTvShowDetails(tvShowId: Int, listener: OutcomeListener<TvShowDetails>)

  fun loadSimilarTvShows(tvShowId: Int, listener: OutcomeListener<Page>)
}