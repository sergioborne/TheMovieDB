package com.sergioborne.themoviedb.tvshowdetails.di

import android.content.Context
import com.sergioborne.themoviedb.common.SchedulerProvider
import com.sergioborne.themoviedb.common.network.TheMovieDBAPI
import com.sergioborne.themoviedb.tvshowdetails.gateway.TvShowDetailsGateway
import com.sergioborne.themoviedb.tvshowdetails.gateway.TvShowDetailsGatewayImpl
import com.sergioborne.themoviedb.tvshowdetails.network.TvShowDetailsService
import com.sergioborne.themoviedb.tvshowdetails.network.TvShowDetailsServiceImpl
import com.sergioborne.themoviedb.tvshowdetails.presenter.TvShowDetailsPresenter
import com.sergioborne.themoviedb.tvshowdetails.presenter.TvShowDetailsPresenterImpl
import com.sergioborne.themoviedb.tvshowdetails.ui.SimilarTvShowsAdapter
import com.sergioborne.themoviedb.tvshowdetails.ui.TvShowsDetailsView
import dagger.Module
import dagger.Provides

@Module
class TvShowDetailsModule {

  @Provides
  fun provideTvShowDetailsService(theMovieDBAPI: TheMovieDBAPI): TvShowDetailsService {
    return TvShowDetailsServiceImpl(theMovieDBAPI)
  }

  @Provides
  fun provideTvShowDetailsGateway(
      schedulerProvider: SchedulerProvider,
      tvShowDetailsService: TvShowDetailsService
  ): TvShowDetailsGateway {
    return TvShowDetailsGatewayImpl(schedulerProvider, tvShowDetailsService)
  }

  @Provides
  fun provideTvShowDetailsPresenter(
      tvShowsDetailsView: TvShowsDetailsView,
      tvShowDetailsGateway: TvShowDetailsGateway
  ): TvShowDetailsPresenter {
    return TvShowDetailsPresenterImpl(tvShowsDetailsView, tvShowDetailsGateway)
  }

  @Provides
  fun provideSimilarTvShowsAdapter(context: Context): SimilarTvShowsAdapter {
    return SimilarTvShowsAdapter(context)
  }
}