package com.sergioborne.themoviedb.mainlist.di

import com.sergioborne.themoviedb.mainlist.ui.MainListActivity
import com.sergioborne.themoviedb.mainlist.ui.MainListView
import dagger.Binds
import dagger.Module

@Module
abstract class MainListViewModule {
  @Binds
  abstract fun provideMainListView(mainListActivity: MainListActivity): MainListView
}