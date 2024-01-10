package com.example.mytestapp.di.share


import org.koin.dsl.module

val preferenceModule = module {

//    single<SharedPreferences> {
//        androidContext().getSharedPreferences(
//            BuildConfig.PREFERENCES_NAME,
//            Context.MODE_PRIVATE
//        )
//    }
//
//    single<AppPreferenceRepository> {
//        AppPreferenceRepositoryImpl(
//            preferences = get(),
//            gson = get()
//        )
//    }
}