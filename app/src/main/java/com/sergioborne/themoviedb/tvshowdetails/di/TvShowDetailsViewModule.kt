package com.sergioborne.themoviedb.tvshowdetails.di

import com.sergioborne.themoviedb.tvshowdetails.ui.TvShowDetailsActivity
import com.sergioborne.themoviedb.tvshowdetails.ui.TvShowsDetailsView
import dagger.Binds
import dagger.Module

@Module
abstract class TvShowDetailsViewModule {
  @Binds
  abstract fun provideMovieDetailsView(tvShowDetailsActivity: TvShowDetailsActivity): TvShowsDetailsView
}