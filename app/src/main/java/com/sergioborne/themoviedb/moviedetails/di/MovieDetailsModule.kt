package com.sergioborne.themoviedb.moviedetails.di

import android.content.Context
import com.sergioborne.themoviedb.common.SchedulerProvider
import com.sergioborne.themoviedb.common.TheMovieDBAPI
import com.sergioborne.themoviedb.moviedetails.gateway.MovieDetailsGateway
import com.sergioborne.themoviedb.moviedetails.gateway.MovieDetailsGatewayImpl
import com.sergioborne.themoviedb.moviedetails.network.MovieDetailsService
import com.sergioborne.themoviedb.moviedetails.network.MovieDetailsServiceImpl
import com.sergioborne.themoviedb.moviedetails.presenter.MovieDetailsPresenter
import com.sergioborne.themoviedb.moviedetails.presenter.MovieDetailsPresenterImpl
import com.sergioborne.themoviedb.moviedetails.ui.MovieDetailsView
import com.sergioborne.themoviedb.moviedetails.ui.SimilarMoviesAdapter
import dagger.Module
import dagger.Provides

@Module
class MovieDetailsModule {

  @Provides
  fun provideMovieDetailsService(theMovieDBAPI: TheMovieDBAPI): MovieDetailsService {
    return MovieDetailsServiceImpl(theMovieDBAPI)
  }

  @Provides
  fun provideMovieDetailsGateway(
      schedulerProvider: SchedulerProvider,
      movieDetailsService: MovieDetailsService
  ): MovieDetailsGateway {
    return MovieDetailsGatewayImpl(schedulerProvider, movieDetailsService)
  }

  @Provides
  fun provideMovieDetailsPresenter(
      movieDetailsView: MovieDetailsView,
      movieDetailsGateway: MovieDetailsGateway
  ): MovieDetailsPresenter {
    return MovieDetailsPresenterImpl(movieDetailsView, movieDetailsGateway)
  }

  @Provides
  fun provideSimilarMoviesAdapter(context: Context): SimilarMoviesAdapter {
    return SimilarMoviesAdapter(context)
  }
}