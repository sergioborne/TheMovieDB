package com.sergioborne.themoviedb.moviedetails.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sergioborne.themoviedb.R
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movie_details.toolbar
import kotlinx.android.synthetic.main.activity_movie_details.toolbarCoverImageView

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsView {

  companion object {
    private const val MOVIE_ID_INTENT_EXTRA = "movieId"
    private const val MOVIE_TITLE_INTENT_EXTRA = "movieTitle"
    private const val MOVIE_POSTER_URL_INTENT_EXTRA = "posterPathUrl"

    @JvmStatic
    fun startActivity(
      context: Context,
      movieId: Int,
      movieTitle: String,
      moviePosterUrl: String,
      options: Bundle
    ) {
      val intent = Intent(context, MovieDetailsActivity::class.java)
      intent.putExtra(MOVIE_ID_INTENT_EXTRA, movieId)
      intent.putExtra(MOVIE_TITLE_INTENT_EXTRA, movieTitle)
      intent.putExtra(MOVIE_POSTER_URL_INTENT_EXTRA, moviePosterUrl)
      context.startActivity(intent, options)
    }
  }

  private var movieId: Int = 0
  private var movieTitle: String = ""
  private var posterPathUrl: String = ""

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movie_details)
    readIntentExtras()
    toolbar.title = movieTitle
    Picasso.get().load(posterPathUrl).into(toolbarCoverImageView)
  }

  private fun readIntentExtras() {
    movieId = intent.getIntExtra(MOVIE_ID_INTENT_EXTRA, 0)
    movieTitle = intent.getStringExtra(MOVIE_TITLE_INTENT_EXTRA)
    posterPathUrl = intent.getStringExtra(MOVIE_POSTER_URL_INTENT_EXTRA)
  }

}