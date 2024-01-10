package com.example.mytestapp.di.share

import android.content.Context
import android.net.ConnectivityManager
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.mytestapp.network.NoConnectivityInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpBuilder(
private val baseUrl: String
) {

    private val timeOut: Long = 30

    fun build(context: Context): OkHttpClient {

        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .addInterceptor(NoConnectivityInterceptor(connectivityManager))
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    }
}