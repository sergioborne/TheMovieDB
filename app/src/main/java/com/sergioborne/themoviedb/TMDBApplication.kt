package com.sergioborne.themoviedb

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.sergioborne.themoviedb.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TMDBApplication : Application(), HasActivityInjector {

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()

    DaggerAppComponent
        .builder()
        .application(this)
        .build()
        .inject(this)

    initStetho()
  }

  private fun initStetho() {
    Stetho.initializeWithDefaults(this)
  }

  override fun activityInjector(): AndroidInjector<Activity>? {
    return dispatchingAndroidInjector
  }
}