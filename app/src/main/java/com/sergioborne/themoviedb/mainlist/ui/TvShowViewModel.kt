package com.sergioborne.themoviedb.mainlist.ui

data class TvShowViewModel(
  val movieId: Int,
  val imageUrl: String,
  val title: String,
  val posterPathUrl: String,
  val rating: Double
)