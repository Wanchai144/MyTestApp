package com.example.mytestapp.presentation.feature.viewmodel.base

import android.os.Bundle

data class BaseNavigationData(
    val destinationId: Int,
    val data: Bundle?
)

data class NavigationPopBackData(
    val destinationId: Int,
    val inclusive: Boolean
)
