package com.example.mytestapp.presentation.feature.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.data.model.UserEntity
import com.example.mytestapp.domain.usecase.DataItemUseCase
import com.example.mytestapp.presentation.extension.toNetworkException
import com.example.mytestapp.presentation.feature.base.*
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log


class HomeMainViewModel(private val dataItemUseCase: DataItemUseCase) :
    BaseViewModel<Any, ViewEffect>() {

    private var _data: MutableLiveData<ViewState<List<UserEntity>>> = MutableLiveData()
    val data: LiveData<ViewState<List<UserEntity>>>
        get() = _data



    private val _ResultSuccess = MutableLiveData<Unit>()
    val resultSuccess: LiveData<Unit> get() = _ResultSuccess

    var inputText: String = ""
    var ids: List<Int> = listOf()

    init {
        loadData()
    }


    fun loadData() = executeUseCase(
        action = {
            dataItemUseCase.executeGetData()
                .catch { exception ->
                    val networkException = exception.toNetworkException()
                    _data.value = Error(networkException)
                }.collect {
                    _data.value = Success(it)
                }
        }, noInternetAction = {
            _data.value = NoInternetState()
        })

    fun addData() = executeUseCase(
        action = {
            dataItemUseCase.executeSaveData(item = inputText)
                .catch { exception ->
                    val networkException = exception.toNetworkException()
                }.collect {
                    _ResultSuccess.value = it
                }
        }, noInternetAction = {

        })

    fun deleteDataByIds()  = executeUseCase(
        action = {
            dataItemUseCase.executeDeleteDataByIds(item = ids)
                .catch { exception ->
                    val networkException = exception.toNetworkException()
                }.collect {
                    _ResultSuccess.value = it
                }
        }, noInternetAction = {

        })




}