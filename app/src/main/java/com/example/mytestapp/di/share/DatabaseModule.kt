package com.example.mytestapp.di.share


import io.realm.Realm
import org.koin.dsl.module


val databaseModule = module {

    single<Realm> {
        Realm.getDefaultInstance()
    }
}