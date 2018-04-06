package com.sergioborne.themoviedb.moviedetails.ui

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

class SimilarMoviesAdapter(
    context: Context) : RecyclerView.Adapter<SimilarMoviesAdapter.SimilarMovieViewHolder>() {

  private var layoutInflater: LayoutInflater = LayoutInflater.from(context)
  private var list: List<SimilarMovieViewModel> = emptyList()

  override fun onCreateViewHolder(
      parent: ViewGroup,
      viewType: Int
  ) = SimilarMovieViewHolder(
      layoutInflater.inflate(R.layout.similar_movies_list_item, parent, false)
  )

  override fun getItemCount() = list.size

  override fun onBindViewHolder(
      holder: SimilarMovieViewHolder,
      position: Int
  ) {
    bindItem(holder, list[position])
  }

  private fun bindItem(
      holder: SimilarMovieViewHolder,
      movieViewModel: SimilarMovieViewModel
  ) {
    holder.movieTitle.text = movieViewModel.title
    Picasso.get().load(movieViewModel.imageUrl).into(holder.movieImage)
  }

  fun updateItems(newList: List<SimilarMovieViewModel>) {
    this.list = newList
    notifyDataSetChanged()
  }

  class SimilarMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val movieImage: ImageView = itemView.movie_image
    val movieTitle: TextView = itemView.movie_title
    val container: View = itemView.container
  }

}