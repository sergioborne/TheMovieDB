package com.sergioborne.themoviedb.mainlist.di

import com.sergioborne.themoviedb.common.SchedulerProvider
import com.sergioborne.themoviedb.common.TheMovieDBAPI
import com.sergioborne.themoviedb.mainlist.gateway.MainListGateway
import com.sergioborne.themoviedb.mainlist.gateway.MainListGatewayImpl
import com.sergioborne.themoviedb.mainlist.network.MoviesService
import com.sergioborne.themoviedb.mainlist.network.MoviesServiceImpl
import com.sergioborne.themoviedb.mainlist.presenter.MainListPresenter
import com.sergioborne.themoviedb.mainlist.presenter.MainListPresenterImpl
import com.sergioborne.themoviedb.mainlist.ui.MainListView
import dagger.Module
import dagger.Provides

@Module
class MainListModule {
  @Provides
  fun provideMoviesService(theMovieDBAPI: TheMovieDBAPI): MoviesService {
    return MoviesServiceImpl(theMovieDBAPI)
  }

  @Provides
  fun provideMainListGateway(
    schedulerProvider: SchedulerProvider,
    moviesService: MoviesService
  ): MainListGateway {
    return MainListGatewayImpl(schedulerProvider, moviesService)
  }

  @Provides
  fun provideMainListPresenter(
    mainListView: MainListView,
    mainListGateway: MainListGateway
  ): MainListPresenter {
    return MainListPresenterImpl(mainListView, mainListGateway)
  }
}