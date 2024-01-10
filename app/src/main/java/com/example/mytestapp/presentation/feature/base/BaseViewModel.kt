package com.example.mytestapp.presentation.feature.base

import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapp.presentation.common.CoroutineContextProvider
import com.example.mytestapp.presentation.feature.viewmodel.base.BaseNavigationData
import com.example.mytestapp.presentation.feature.viewmodel.base.NavigationPopBackData
import com.example.mytestapp.utils.Connectivity
import com.example.mytestapp.utils.MutableLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel<T : Any, E> : ViewModel(), KoinComponent,Disposer {

    protected val coroutineContext: CoroutineContextProvider by inject()
    private val connectivity: Connectivity by inject()

    val fitsSystemWindows = MutableLiveData<Boolean>(true)

    val navigationEvent: MutableLiveEvent<BaseNavigationData> = MutableLiveEvent()

    val navigationPopBackStack: MutableLiveEvent<Boolean> = MutableLiveEvent()

    val navigationPopBackStackDestinationId: MutableLiveEvent<NavigationPopBackData> = MutableLiveEvent()

    var screenName: String = ""

//    val analyticsManager : AnalyticsManager by lazy {
//        val view = ViewAnalyticsData(
//            screenName = screenName,
//            screenClass = this::class.java.simpleName
//        )
//        AnalyticsManager(view)
//    }

    override val disposeBag: CompositeDisposable = CompositeDisposable()

    protected val _viewState = MutableLiveData<ViewState<T>>()
    val viewState: LiveData<ViewState<T>>
        get() = _viewState

    protected val _viewEffects = MutableLiveData<E>()
    val viewEffects: LiveData<E>
        get() = _viewEffects


    internal fun addDisposableInternal(d: Disposable) {
        this.disposeBag.add(d)
    }

    override fun onCleared() {
        super.onCleared()
        this.disposeBag.clear()
    }

    fun <T>onNavigate(destinationId: Int, data: (T?)) {
        val bundle = bundleOf(DATA_CONTEXT_KEY to data)
        navigationEvent.emit(BaseNavigationData(destinationId, bundle))
    }

    fun onNavigate(destinationId: Int) {
        onNavigate(destinationId, null)
    }

    fun onNavigatePopBackStack(isPopBackStack: Boolean) {
        navigationPopBackStack.emit(isPopBackStack)
    }

    fun onNavigatePopBackStack(destinationId: Int, inclusive: Boolean) {
        val event = NavigationPopBackData(
            destinationId = destinationId,
            inclusive = inclusive
        )
        navigationPopBackStackDestinationId.emit(event)
    }

    companion object {
        const val DATA_CONTEXT_KEY = "dataContext"
    }

    protected fun executeUseCase(action: suspend () -> Unit, noInternetAction: () -> Unit) {
//        _viewState.value = Loading()
        if (connectivity.hasNetworkAccess()) {
            launch {  action() }
        } else {
            noInternetAction()
        }
    }

    protected fun executeUseCase(action: suspend () -> Unit) {
        _viewState.value = Loading()
        launch { action() }
    }
}



interface Disposer {
    val disposeBag: CompositeDisposable
}