package com.sergioborne.themoviedb.mainlist.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sergioborne.themoviedb.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_list_tv_show_item.view.*

class MainListAdapter(context: Context) : RecyclerView.Adapter<MainListAdapter.TvShowViewHolder>() {

  private var layoutInflater: LayoutInflater = LayoutInflater.from(context)
  private var list: List<TvShowViewModel> = emptyList()
  private var listener: ((View, Int, String, String) -> Unit)? = null

  override fun onCreateViewHolder(
      parent: ViewGroup,
      viewType: Int
  ) = TvShowViewHolder(
      layoutInflater.inflate(R.layout.main_list_tv_show_item, parent, false)
  )

  override fun getItemCount() = list.size

  override fun onBindViewHolder(
      holder: TvShowViewHolder,
      position: Int
  ) {
    bindItem(holder, list[position])
  }

  private fun bindItem(
      holder: TvShowViewHolder,
      tvShowViewModel: TvShowViewModel
  ) {
    holder.tvShowTitle.text = tvShowViewModel.title
    Picasso.get().load(tvShowViewModel.posterPathUrl).placeholder(
        R.drawable.ic_photo_black_48dp).into(holder.tvShowImage)
    holder.container.setOnClickListener {
      listener?.invoke(
          it, tvShowViewModel.movieId, tvShowViewModel.title, tvShowViewModel.posterPathUrl
      )
    }
  }

  fun appendItems(newItems: List<TvShowViewModel>) {
    val sizeBefore = this.list.size
    this.list += newItems
    notifyItemRangeInserted(sizeBefore, this.list.size)
  }

  fun clearItems() {
    this.list = emptyList()
    notifyDataSetChanged()
  }

  fun setClickListener(listener: (View, Int, String, String) -> Unit) {
    this.listener = listener
  }

  class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvShowImage: ImageView = itemView.tv_show_image
    val tvShowTitle: TextView = itemView.tv_show_title
    val container: View = itemView.container
  }

}