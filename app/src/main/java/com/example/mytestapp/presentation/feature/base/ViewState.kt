package com.example.mytestapp.presentation.feature.base

import com.example.mytestapp.presentation.extension.NetworkException

sealed class ViewState<out T : Any>
class Success<out T : Any>(val data: T) : ViewState<T>()
class Error<out T : Any>(var message: NetworkException?) : ViewState<T>()
class Loading<out T : Any> : ViewState<T>()
class NoInternetState<T : Any> : ViewState<T>()
