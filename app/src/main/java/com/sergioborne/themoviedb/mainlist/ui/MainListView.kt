package com.sergioborne.themoviedb.mainlist.ui

interface MainListView {

  fun updateMoviesList(list: List<MovieViewModel>)

  fun showLoadingIndicator()

  fun hideLoadingIndicator()

  fun showError(messageResId: Int)

  //fun openDetails(movieId: Int, movieTitle: String)
}