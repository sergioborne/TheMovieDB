package com.sergioborne.themoviedb.moviedetails.ui


interface MovieDetailsView {

  fun showMovieDetails(description:String)

  fun showSimilarMovies(list: List<SimilarMovieViewModel>)

  fun showError(messageResId: Int)
}