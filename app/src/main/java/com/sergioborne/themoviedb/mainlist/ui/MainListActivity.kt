package com.sergioborne.themoviedb.mainlist.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.sergioborne.themoviedb.R
import com.sergioborne.themoviedb.mainlist.presenter.MainListPresenter
import com.sergioborne.themoviedb.tvshowdetails.ui.TvShowDetailsActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.main_list_activity.*
import javax.inject.Inject

class MainListActivity : AppCompatActivity(), MainListView {

  companion object {
    const val COLUMN_NUMBER = 3
  }

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
    adapter.setClickListener { view, tvShowId, tvShowTitle, tvShowImageUrl ->
      openDetails(
          view, tvShowId, tvShowTitle, tvShowImageUrl
      )
    }
    tv_shows_list.adapter = adapter
    gridLayoutManager = GridLayoutManager(this, COLUMN_NUMBER, GridLayoutManager.VERTICAL, false)
    tv_shows_list.layoutManager = gridLayoutManager
    tv_shows_list.addOnScrollListener(BottomScrollListener())
  }

  override fun showLoadingIndicator() {
    swipe_refresh_layout.isRefreshing = true
  }

  override fun hideLoadingIndicator() {
    swipe_refresh_layout.isRefreshing = false
  }

  override fun showError(messageResId: Int) {
    Snackbar.make(tv_shows_list, messageResId, Snackbar.LENGTH_SHORT).show()
  }

  override fun updateList(list: List<TvShowViewModel>) {
    adapter.appendItems(list)
  }

  override fun clearList() {
    adapter.clearItems()
  }

  private fun openDetails(
      viewClicked: View,
      tvShowId: Int,
      tvShowTitle: String,
      tvShowImageUrl: String
  ) {
    val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
        this,
        viewClicked.findViewById(R.id.tv_show_image),
        "coverImage"
    )
    TvShowDetailsActivity.startActivity(
        this, tvShowId, tvShowTitle, tvShowImageUrl, activityOptions.toBundle()!!
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
