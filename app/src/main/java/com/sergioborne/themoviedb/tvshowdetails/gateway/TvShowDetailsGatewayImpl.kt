package com.sergioborne.themoviedb.tvshowdetails.gateway

import com.sergioborne.themoviedb.common.OutcomeListener
import com.sergioborne.themoviedb.common.SchedulerProvider
import com.sergioborne.themoviedb.tvshowdetails.data.TvShowDetails
import com.sergioborne.themoviedb.tvshowdetails.network.TvShowDetailsService
import com.sergioborne.tmdbkotlinchallenge.data.Page

class TvShowDetailsGatewayImpl(private val schedulerProvider: SchedulerProvider,
    private val service: TvShowDetailsService) : TvShowDetailsGateway {

  override fun loadTvShowDetails(tvShowId: Int, listener: OutcomeListener<TvShowDetails>) {
    service.getTvShowDetails(tvShowId).subscribeOn(
        schedulerProvider.backgroundThreadScheduler()).observeOn(
        schedulerProvider.mainThreadScheduler()).subscribe(listener::success, listener::error)
  }

  override fun loadSimilarTvShows(tvShowId: Int, listener: OutcomeListener<Page>) {
    service.getSimilarTvShows(tvShowId).subscribeOn(
        schedulerProvider.backgroundThreadScheduler()).observeOn(
        schedulerProvider.mainThreadScheduler()).subscribe(listener::success, listener::error)
  }
}