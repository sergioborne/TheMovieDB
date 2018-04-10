package com.sergioborne.themoviedb.tvshowdetails.ui


interface TvShowsDetailsView {

  fun showTvShowDetails(description:String)

  fun showSimilarTvShows(list: List<SimilarTvShowViewModel>)

  fun showError(messageResId: Int)
}