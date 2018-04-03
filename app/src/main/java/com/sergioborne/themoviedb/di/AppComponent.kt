package com.sergioborne.themoviedb.di

import com.sergioborne.themoviedb.TMDBApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
  AndroidInjectionModule::class, AppModule::class, BuildersModule::class, NetworkModule::class]
)
interface AppComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: TMDBApplication): Builder

    fun build(): AppComponent
  }

  fun inject(app: TMDBApplication)
}