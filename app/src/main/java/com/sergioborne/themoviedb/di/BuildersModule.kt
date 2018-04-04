package com.sergioborne.themoviedb.di

import com.sergioborne.themoviedb.mainlist.di.MainListModule
import com.sergioborne.themoviedb.mainlist.di.MainListViewModule
import com.sergioborne.themoviedb.mainlist.ui.MainListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class BuildersModule {

  @ContributesAndroidInjector(modules = [MainListModule::class, MainListViewModule::class])
  abstract fun bindMainListActivity(): MainListActivity
}
