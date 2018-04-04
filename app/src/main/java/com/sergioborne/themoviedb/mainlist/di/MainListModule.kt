package com.sergioborne.themoviedb.mainlist.di

import com.sergioborne.themoviedb.common.TheMovieDBAPI
import com.sergioborne.themoviedb.mainlist.network.MoviesService
import com.sergioborne.themoviedb.mainlist.network.MoviesServiceImpl
import dagger.Module
import dagger.Provides

@Module
class MainListModule {
  @Provides
  fun provideMoviesService(theMovieDBAPI: TheMovieDBAPI): MoviesService {
    return MoviesServiceImpl(theMovieDBAPI)
  }
}