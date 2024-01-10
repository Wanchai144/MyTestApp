package com.example.mytestapp.network

import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NoConnectivityInterceptor constructor(
    private val connectivityManager: ConnectivityManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (isConnected()) {
            return chain.proceed(chain.request())
        }
        throw NoConnectivityException()
    }

    private fun isConnected(): Boolean {
        return connectivityManager.activeNetworkInfo != null
                && connectivityManager.activeNetworkInfo!!.isConnected
    }

}

class NoConnectivityException : IOException()