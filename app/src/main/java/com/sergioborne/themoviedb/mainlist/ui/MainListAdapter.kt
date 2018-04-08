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
import kotlinx.android.synthetic.main.main_list_movie_item.view.*

class MainListAdapter(context: Context) : RecyclerView.Adapter<MainListAdapter.MovieViewHolder>() {

  private var layoutInflater: LayoutInflater = LayoutInflater.from(context)
  private var list: List<MovieViewModel> = emptyList()
  private var listener: ((View, Int, String, String) -> Unit)? = null

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ) = MovieViewHolder(
      layoutInflater.inflate(R.layout.main_list_movie_item, parent, false)
  )

  override fun getItemCount() = list.size

  override fun onBindViewHolder(
    holder: MovieViewHolder,
    position: Int
  ) {
    bindItem(holder, list[position])
  }

  private fun bindItem(
    holder: MovieViewHolder,
    movieViewModel: MovieViewModel
  ) {
    holder.movieTitle.text = movieViewModel.title
    Picasso.get().load(movieViewModel.posterPathUrl).placeholder(R.drawable.ic_photo_black_48dp).into(holder.movieImage)
    holder.container.setOnClickListener {
      listener?.invoke(
          it, movieViewModel.movieId, movieViewModel.title, movieViewModel.posterPathUrl
      )
    }
  }

  fun appendItems(newItems: List<MovieViewModel>) {
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

  class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val movieImage: ImageView = itemView.movie_image
    val movieTitle: TextView = itemView.movie_title
    val container: View = itemView.container
  }

}