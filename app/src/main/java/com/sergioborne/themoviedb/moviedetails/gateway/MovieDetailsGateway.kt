package com.sergioborne.themoviedb.moviedetails.gateway

import com.sergioborne.themoviedb.common.OutcomeListener
import com.sergioborne.themoviedb.moviedetails.data.MovieDetails
import com.sergioborne.tmdbkotlinchallenge.data.Page

interface MovieDetailsGateway {

  fun loadMovieDetails(movieId: Int, listener: OutcomeListener<MovieDetails>)

  fun loadSimilarMovies(movieId: Int, listener: OutcomeListener<Page>)
}