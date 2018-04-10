package com.sergioborne.themoviedb.tvshowdetails.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sergioborne.themoviedb.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.similar_tv_show_list_item.view.*

class SimilarTvShowsAdapter(
    context: Context
) : RecyclerView.Adapter<SimilarTvShowsAdapter.SimilarTvShowViewHolder>() {

  private var layoutInflater: LayoutInflater = LayoutInflater.from(context)
  private var list: List<SimilarTvShowViewModel> = emptyList()

  override fun onCreateViewHolder(
      parent: ViewGroup,
      viewType: Int
  ) = SimilarTvShowViewHolder(
      layoutInflater.inflate(R.layout.similar_tv_show_list_item, parent, false)
  )

  override fun getItemCount() = list.size

  override fun onBindViewHolder(
      holder: SimilarTvShowViewHolder,
      position: Int
  ) {
    bindItem(holder, list[position])
  }

  private fun bindItem(
      holder: SimilarTvShowViewHolder,
      tvShowViewModel: SimilarTvShowViewModel
  ) {
    holder.tvShowTitle.text = tvShowViewModel.title
    Picasso.get().load(tvShowViewModel.imageUrl).placeholder(R.drawable.ic_photo_black_48dp).into(
        holder.tvShowImage
    )
  }

  fun updateItems(newList: List<SimilarTvShowViewModel>) {
    this.list = newList
    notifyDataSetChanged()
  }

  class SimilarTvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvShowImage: ImageView = itemView.similar_tv_show_image
    val tvShowTitle: TextView = itemView.similar_tv_show_title
    val container: View = itemView.container
  }

}