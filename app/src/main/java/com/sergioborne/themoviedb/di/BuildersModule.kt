package com.sergioborne.themoviedb.di

import android.app.ListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
class BuildersModule {
  @Module
  abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun bindListActivity(): ListActivity
  }
}