package com.sergioborne.themoviedb.mainlist.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sergioborne.themoviedb.R

class MainListActivity : AppCompatActivity(), MainListView {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list)
  }
}
