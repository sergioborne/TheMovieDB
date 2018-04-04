package com.sergioborne.themoviedb.mainlist.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sergioborne.themoviedb.R


class MainListAdapter(context: Context) : RecyclerView.Adapter<MainListAdapter.MovieViewHolder>() {

  var layoutInflater: LayoutInflater = LayoutInflater.from(context)
  var list: List<MovieViewModel> = emptyList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
      layoutInflater.inflate(R.layout.main_list_movie_item, parent, false))

  override fun getItemCount() = list.size

  override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

  }

  fun updateItems(newList: List<MovieViewModel>) {
    this.list = newList
    notifyDataSetChanged()
  }

  class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}