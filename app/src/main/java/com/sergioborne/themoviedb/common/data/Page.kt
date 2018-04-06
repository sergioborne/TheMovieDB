package com.sergioborne.tmdbkotlinchallenge.data

data class Page(val page: Int, val total_results: Int, val total_pages: Int,
    val results: List<Movie>)