package com.example.mytestapp

import android.app.Application
import com.example.mytestapp.di.appModule
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}