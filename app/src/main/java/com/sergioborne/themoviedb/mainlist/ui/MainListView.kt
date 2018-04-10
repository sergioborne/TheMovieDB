package com.sergioborne.themoviedb.mainlist.ui

interface MainListView {

  fun updateList(list: List<TvShowViewModel>)

  fun showLoadingIndicator()

  fun hideLoadingIndicator()

  fun showError(messageResId: Int)

  fun clearList()
}