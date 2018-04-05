package com.sergioborne.themoviedb.di

import com.sergioborne.themoviedb.mainlist.di.MainListModule
import com.sergioborne.themoviedb.mainlist.di.MainListViewModule
import com.sergioborne.themoviedb.mainlist.ui.MainListActivity
import com.sergioborne.themoviedb.moviedetails.di.MovieDetailsModule
import com.sergioborne.themoviedb.moviedetails.di.MovieDetailsViewModule
import com.sergioborne.themoviedb.moviedetails.ui.MovieDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class BuildersModule {

  @ContributesAndroidInjector(modules = [MainListModule::class, MainListViewModule::class])
  abstract fun bindMainListActivity(): MainListActivity

  @ContributesAndroidInjector(modules = [MovieDetailsModule::class, MovieDetailsViewModule::class])
  abstract fun bindMovieDetailsActivity(): MovieDetailsActivity
}
