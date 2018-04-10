package com.sergioborne.themoviedb.mainlist.presenterimport android.support.annotation.VisibleForTestingimport com.sergioborne.themoviedb.Rimport com.sergioborne.themoviedb.common.OutcomeListenerimport com.sergioborne.themoviedb.common.data.ImageConfiguration.Companion.IMAGE_URL_BASEimport com.sergioborne.themoviedb.mainlist.gateway.MainListGatewayimport com.sergioborne.themoviedb.mainlist.ui.MainListViewimport com.sergioborne.themoviedb.mainlist.ui.TvShowViewModelimport com.sergioborne.tmdbkotlinchallenge.data.Pageimport com.sergioborne.tmdbkotlinchallenge.data.TvShowclass MainListPresenterImpl(    private val mainListView: MainListView,    private val gateway: MainListGateway) : MainListPresenter {  @VisibleForTesting  var isFetchingData = false  @VisibleForTesting  var currentPage = 1  @VisibleForTesting  var isThereMorePages = true  override fun init() {    mainListView.showLoadingIndicator()    loadFirstPage()  }  override fun refreshList() {    loadFirstPage()  }  private fun loadFirstPage() {    currentPage = 1    mainListView.clearList()    loadPage(currentPage)  }  private fun loadPage(page: Int) {    gateway.loadTvShows(page, object : OutcomeListener<Page> {      override fun success(page: Page) {        mainListView.hideLoadingIndicator()        mainListView.updateList(createViewModelsList(page.results))        isThereMorePages = page.total_pages > currentPage        isFetchingData = false      }      override fun error(error: Throwable) {        mainListView.showError(R.string.default_error_message)        mainListView.hideLoadingIndicator()        isFetchingData = false      }    })  }  override fun bottomListReached() {    if (isNotFetchingData() && isThereMorePages) {      isFetchingData = true      currentPage += 1      loadPage(currentPage)    }  }  private fun isNotFetchingData() = !isFetchingData  private fun createViewModelsList(tvShowList: List<TvShow>): List<TvShowViewModel> {    return tvShowList.map { this.createViewModelFromTvShow(it) }  }  private fun createViewModelFromTvShow(tvShow: TvShow): TvShowViewModel {    return TvShowViewModel(        tvShow.id, "", tvShow.name, IMAGE_URL_BASE + tvShow.poster_path    )  }}