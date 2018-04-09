package com.sergioborne.themoviedb

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.sergioborne.themoviedb.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
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

    initStethoIfDebug()
    initLeakCanary()
  }

  private fun initLeakCanary() {
    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return
    }
    LeakCanary.install(this);
  }

  private fun initStethoIfDebug() {
    if (BuildConfig.DEBUG)
      Stetho.initializeWithDefaults(this)
  }

  override fun activityInjector(): AndroidInjector<Activity>? {
    return dispatchingAndroidInjector
  }
}