package com.sergioborne.themoviedb.moviedetails.data

data class Genre(
  val id: Int? = null,
  val name: String? = null,
  val additionalProperties: HashMap<String, Any> = HashMap()
)