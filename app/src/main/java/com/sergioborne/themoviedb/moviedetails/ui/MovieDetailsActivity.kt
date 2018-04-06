package com.sergioborne.themoviedb.moviedetails.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.sergioborne.themoviedb.R
import com.sergioborne.themoviedb.moviedetails.presenter.MovieDetailsPresenter
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.main_list_activity.*
import kotlinx.android.synthetic.main.movie_details_activity.*
import kotlinx.android.synthetic.main.movie_details_content.*
import javax.inject.Inject

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

  @Inject
  lateinit var presenter: MovieDetailsPresenter
  @Inject
  lateinit var adapter: SimilarMoviesAdapter

  private var movieId: Int = 0
  private var movieTitle: String = ""
  private var posterPathUrl: String = ""

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.movie_details_activity)
    readIntentExtras()
    setupSimilarMoviesRecyclerView()
    title = movieTitle
    Picasso.get().load(posterPathUrl).into(toolbar_poster_image_view)
    presenter.init(movieId)
  }

  private fun setupSimilarMoviesRecyclerView() {
    similar_movies.adapter = adapter
    similar_movies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
  }

  private fun readIntentExtras() {
    movieId = intent.getIntExtra(MOVIE_ID_INTENT_EXTRA, 0)
    movieTitle = intent.getStringExtra(MOVIE_TITLE_INTENT_EXTRA)
    posterPathUrl = intent.getStringExtra(MOVIE_POSTER_URL_INTENT_EXTRA)
  }

  override fun showMovieDetails(description: String) {
    movie_description.text = description
  }

  override fun showSimilarMovies(list: List<SimilarMovieViewModel>) {
    adapter.updateItems(list)
  }

  override fun showError(messageResId: Int) {
    Snackbar.make(movies_list, messageResId, Snackbar.LENGTH_SHORT).show()
  }

}