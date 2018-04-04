package com.sergioborne.themoviedb.mainlist.ui

interface MainListView {

  fun showLoadingIndicator()

  fun hideLoadingIndicator()

  fun showError(messageResId: Int)
}