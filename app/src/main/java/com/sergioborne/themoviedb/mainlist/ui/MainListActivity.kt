package com.sergioborne.themoviedb.mainlist.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.sergioborne.themoviedb.R
import com.sergioborne.themoviedb.mainlist.presenter.MainListPresenter
import com.sergioborne.themoviedb.moviedetails.ui.MovieDetailsActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class MainListActivity : AppCompatActivity(), MainListView {

  @Inject
  lateinit var presenter: MainListPresenter
  @Inject
  lateinit var adapter: MainListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list)

    setupRecyclerView()
    presenter.init()
  }

  private fun setupRecyclerView() {
    swipe_refresh_layout.setOnRefreshListener { presenter.refreshList() }
    adapter.setClickListener { (movieId, movieTitle) -> presenter.itemClicked(movieId, movieTitle) }
    movies_list.adapter = adapter
    movies_list.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
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
    adapter.updateItems(list)
  }

  override fun openDetails(movieId: Int, movieTitle: String) {
    MovieDetailsActivity.startActivity(this, movieId, movieTitle)
  }
}
