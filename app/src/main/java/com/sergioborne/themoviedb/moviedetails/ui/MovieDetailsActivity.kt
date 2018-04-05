package com.sergioborne.themoviedb.moviedetails.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sergioborne.themoviedb.R
import dagger.android.AndroidInjection


class MovieDetailsActivity : AppCompatActivity(), MovieDetailsView {

  companion object {
    private const val MOVIE_ID_INTENT_EXTRA = "movieId"
    private const val MOVIE_TITLE_INTENT_EXTRA = "movieTitle"

    @JvmOverloads
    @JvmStatic
    fun startActivity(context: Context, movieId: Int, movieTitle: String) {
      val intent = Intent(context, MovieDetailsActivity::class.java)
      intent.putExtra(MOVIE_ID_INTENT_EXTRA, movieId)
      intent.putExtra(MOVIE_TITLE_INTENT_EXTRA, movieTitle)
      context.startActivity(intent)
    }
  }

  private var movieId: Int = 0
  private var movieTitle: String = ""

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movie_details)
    readIntentExtras()
  }

  private fun readIntentExtras() {
    movieId = intent.getIntExtra(MOVIE_ID_INTENT_EXTRA, 0)
    movieTitle = intent.getStringExtra(MOVIE_TITLE_INTENT_EXTRA)
  }

}