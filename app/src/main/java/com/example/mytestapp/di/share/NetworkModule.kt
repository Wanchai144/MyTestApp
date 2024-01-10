package com.example.mytestapp.di.share


import com.example.mytestapp.BuildConfig
import com.example.mytestapp.data.source.remote.api.service.APIService
import com.example.mytestapp.presentation.common.CoroutineContextProvider
import com.example.mytestapp.utils.Connectivity
import com.example.mytestapp.utils.ConnectivityImpl
import com.google.gson.Gson
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Gson()
    }

    single { CoroutineContextProvider() }

    single<CallAdapter.Factory> { RxJava2CallAdapterFactory.create() }

    factory<Connectivity> { ConnectivityImpl(androidContext()) }

    single<Converter.Factory> { GsonConverterFactory.create() }

    single { RetrofitBuilder(androidContext(), get(), get()) }

    single {
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    //Api interface
    single<APIService> { get<RetrofitBuilder>().build(BuildConfig.BASE_API_URL) }

}