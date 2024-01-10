package com.example.mytestapp.di.feature

import com.example.mytestapp.data.repository.UserRepository
import com.example.mytestapp.data.repository.UserRepositoryImpl
import com.example.mytestapp.domain.usecase.DataItemUseCase
import com.example.mytestapp.domain.usecase.DataItemUseCaseImpl
import com.example.mytestapp.presentation.feature.main.HomeMainViewModel
import com.example.mytestapp.presentation.feature.viewmodel.share.ShareMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {

    viewModel {
        HomeMainViewModel(dataItemUseCase = get())
    }

    factory<DataItemUseCase> {
        DataItemUseCaseImpl(userRepository = get())
    }


    factory<UserRepository> {
        UserRepositoryImpl(realm = get())
    }


    viewModel {
        ShareMainViewModel()
    }


}