package com.sergioborne.themoviedb.moviedetails.di

import com.sergioborne.themoviedb.moviedetails.ui.MovieDetailsActivity
import com.sergioborne.themoviedb.moviedetails.ui.MovieDetailsView
import dagger.Binds
import dagger.Module

@Module
abstract class MovieDetailsViewModule {
  @Binds
  abstract fun provideMovieDetailsView(movieDetailsActivity: MovieDetailsActivity): MovieDetailsView
}