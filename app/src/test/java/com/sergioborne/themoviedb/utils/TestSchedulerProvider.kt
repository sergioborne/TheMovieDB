package com.sergioborne.themoviedb.utils

import com.sergioborne.themoviedb.common.SchedulerProvider
import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider : SchedulerProvider {
  override fun mainThreadScheduler() = Schedulers.trampoline()

  override fun backgroundThreadScheduler() = Schedulers.trampoline()
}