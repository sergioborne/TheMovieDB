package com.sergioborne.themoviedb.mainlist.di

import android.content.Context
import com.sergioborne.themoviedb.common.SchedulerProvider
import com.sergioborne.themoviedb.common.network.TheMovieDBAPI
import com.sergioborne.themoviedb.mainlist.gateway.MainListGateway
import com.sergioborne.themoviedb.mainlist.gateway.MainListGatewayImpl
import com.sergioborne.themoviedb.mainlist.network.TvShowsService
import com.sergioborne.themoviedb.mainlist.network.TvShowsServiceImpl
import com.sergioborne.themoviedb.mainlist.presenter.MainListPresenter
import com.sergioborne.themoviedb.mainlist.presenter.MainListPresenterImpl
import com.sergioborne.themoviedb.mainlist.ui.MainListAdapter
import com.sergioborne.themoviedb.mainlist.ui.MainListView
import dagger.Module
import dagger.Provides

@Module
class MainListModule {
  @Provides
  fun provideTvShowsService(theMovieDBAPI: TheMovieDBAPI): TvShowsService {
    return TvShowsServiceImpl(theMovieDBAPI)
  }

  @Provides
  fun provideMainListGateway(
    schedulerProvider: SchedulerProvider,
    tvShowsService: TvShowsService
  ): MainListGateway {
    return MainListGatewayImpl(schedulerProvider, tvShowsService)
  }

  @Provides
  fun provideMainListPresenter(
    mainListView: MainListView,
    mainListGateway: MainListGateway
  ): MainListPresenter {
    return MainListPresenterImpl(mainListView, mainListGateway)
  }

  @Provides
  fun provideMainListAdapter(context: Context): MainListAdapter {
    return MainListAdapter(context)
  }
}