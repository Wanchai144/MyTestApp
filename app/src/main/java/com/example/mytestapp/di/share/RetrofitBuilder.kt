package com.example.mytestapp.di.share

import android.content.Context
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

class RetrofitBuilder(
    val context: Context,
    val converterFactory: Converter.Factory,
    val adapterFactory: CallAdapter.Factory
) {

    inline fun <reified T> build(baseUrl: String): T {
        val okHttpClient = OkHttpBuilder(baseUrl).build(context)
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(adapterFactory)
            .build()
            .create(T::class.java)
    }
}