package com.sergioborne.themoviedb.mainlist.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.sergioborne.themoviedb.R
import com.sergioborne.themoviedb.mainlist.presenter.MainListPresenter
import com.sergioborne.themoviedb.moviedetails.ui.MovieDetailsActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.main_list_activity.*
import javax.inject.Inject
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.RecyclerView
import android.view.View

class MainListActivity : AppCompatActivity(), MainListView {

  @Inject
  lateinit var presenter: MainListPresenter
  @Inject
  lateinit var adapter: MainListAdapter

  private lateinit var gridLayoutManager: GridLayoutManager

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_list_activity)

    setupRecyclerView()
    presenter.init()
  }

  private fun setupRecyclerView() {
    swipe_refresh_layout.setOnRefreshListener { presenter.refreshList() }
    adapter.setClickListener { view, movieId, movieTitle, movieImageUrl ->
      openDetails(
          view, movieId, movieTitle, movieImageUrl
      )
    }
    movies_list.adapter = adapter
    gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
    movies_list.layoutManager = gridLayoutManager
    movies_list.addOnScrollListener(BottomScrollListener())
  }

  override fun showLoadingIndicator() {
    swipe_refresh_layout.isRefreshing = true
  }

  override fun hideLoadingIndicator() {
    swipe_refresh_layout.isRefreshing = false
  }

  override fun showError(messageResId: Int) {
    Snackbar.make(movies_list, messageResId, Snackbar.LENGTH_SHORT).show()
  }

  override fun updateMoviesList(list: List<MovieViewModel>) {
    adapter.appendItems(list)
  }

  override fun clearMovieList() {
    adapter.clearItems()
  }

  private fun openDetails(
    viewClicked: View,
    movieId: Int,
    movieTitle: String,
    movieImageUrl: String
  ) {
    val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
        this,
        viewClicked.findViewById(R.id.movie_image),
        "coverImage"
    )
    MovieDetailsActivity.startActivity(
        this, movieId, movieTitle, movieImageUrl, activityOptions.toBundle()!!
    )
  }

  private inner class BottomScrollListener : RecyclerView.OnScrollListener() {

    private val BOTTOM_LIST_THRESHOLD = 10

    override fun onScrolled(
      recyclerView: RecyclerView?,
      dx: Int,
      dy: Int
    ) {
      val lastVisibleItemPosition = gridLayoutManager.findLastCompletelyVisibleItemPosition()
      val modelsCount = adapter.itemCount

      if (lastVisibleItemPosition > modelsCount - BOTTOM_LIST_THRESHOLD) {
        presenter.bottomListReached()
      }
    }
  }
}
