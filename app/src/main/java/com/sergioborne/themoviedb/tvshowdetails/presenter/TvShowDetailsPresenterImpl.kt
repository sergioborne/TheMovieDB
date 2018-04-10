package com.sergioborne.themoviedb.tvshowdetails.presenter

import com.sergioborne.themoviedb.R
import com.sergioborne.themoviedb.common.OutcomeListener
import com.sergioborne.themoviedb.common.data.ImageConfiguration
import com.sergioborne.themoviedb.tvshowdetails.data.TvShowDetails
import com.sergioborne.themoviedb.tvshowdetails.gateway.TvShowDetailsGateway
import com.sergioborne.themoviedb.tvshowdetails.ui.SimilarTvShowViewModel
import com.sergioborne.themoviedb.tvshowdetails.ui.TvShowsDetailsView
import com.sergioborne.tmdbkotlinchallenge.data.Page
import com.sergioborne.tmdbkotlinchallenge.data.TvShow

class TvShowDetailsPresenterImpl(
    private val tvShowsDetailsView: TvShowsDetailsView,
    private val gateway: TvShowDetailsGateway
) : TvShowDetailsPresenter {

  override fun init(tvShowId: Int) {
    updateDetails(tvShowId)
  }

  private fun updateDetails(tvShowId: Int) {
    loadTvShowDetails(tvShowId)
    loadSimilarTvShows(tvShowId)
  }

  private fun loadTvShowDetails(tvShowId: Int) {
    gateway.loadTvShowDetails(tvShowId, object : OutcomeListener<TvShowDetails> {
      override fun success(tvShowDetails: TvShowDetails) {
        tvShowsDetailsView.showTvShowDetails(tvShowDetails.overview ?: "")
      }

      override fun error(error: Throwable) {
        tvShowsDetailsView.showError(R.string.default_error_message)
      }
    })
  }

  private fun loadSimilarTvShows(tvShowId: Int) {
    gateway.loadSimilarTvShows(tvShowId, object : OutcomeListener<Page> {
      override fun success(page: Page) {
        tvShowsDetailsView.showSimilarTvShows(createViewModelsList(page.results))
      }

      override fun error(error: Throwable) {
        tvShowsDetailsView.showError(R.string.default_error_message)
      }
    })
  }

  private fun createViewModelsList(tvShowList: List<TvShow>): List<SimilarTvShowViewModel> {
    return tvShowList.map { this.createViewModelFromTvShow(it) }
  }

  private fun createViewModelFromTvShow(tvShow: TvShow): SimilarTvShowViewModel {
    return SimilarTvShowViewModel(tvShow.name,
        ImageConfiguration.IMAGE_URL_BASE + tvShow.poster_path)
  }
}