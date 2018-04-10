package com.sergioborne.themoviedb.di

import com.sergioborne.themoviedb.mainlist.di.MainListModule
import com.sergioborne.themoviedb.mainlist.di.MainListViewModule
import com.sergioborne.themoviedb.mainlist.ui.MainListActivity
import com.sergioborne.themoviedb.tvshowdetails.di.TvShowDetailsModule
import com.sergioborne.themoviedb.tvshowdetails.di.TvShowDetailsViewModule
import com.sergioborne.themoviedb.tvshowdetails.ui.TvShowDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class BuildersModule {

  @ContributesAndroidInjector(modules = [MainListModule::class, MainListViewModule::class])
  abstract fun bindMainListActivity(): MainListActivity

  @ContributesAndroidInjector(
      modules = [TvShowDetailsModule::class, TvShowDetailsViewModule::class])
  abstract fun bindTvShowDetailsActivity(): TvShowDetailsActivity
}
