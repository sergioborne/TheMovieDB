package com.sergioborne.themoviedb.utils

import org.mockito.ArgumentCaptor
import org.mockito.Mockito

inline fun <reified T : Any> argumentCaptor(): ArgumentCaptor<T> =
    ArgumentCaptor.forClass(T::class.java)

fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

fun <T> any(): T {
  Mockito.any<T>()
  return uninitialized()
}

private fun <T> uninitialized(): T = null as T

fun <T> eq(obj: T): T = Mockito.eq<T>(obj)

inline fun <reified T : Any> mock() = Mockito.mock(T::class.java)