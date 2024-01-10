package com.example.mytestapp.di

import com.example.mytestapp.di.feature.mainModule
import com.example.mytestapp.di.share.databaseModule
import com.example.mytestapp.di.share.networkModule


val appModule = listOf(
    networkModule,
    mainModule,
    databaseModule,
)