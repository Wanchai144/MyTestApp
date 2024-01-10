package com.example.mytestapp.presentation.feature.viewmodel.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val fitsSystemWindows = MutableLiveData<Boolean>(true)

//    val alertDialog: MutableLiveEvent<AlertDialog> = MutableLiveEvent()

//    val navigationEvent: MutableLiveEvent<BaseNavigationData> = MutableLiveEvent()
//
//    val navigationPopBackStack: MutableLiveEvent<Boolean> = MutableLiveEvent()
//
//    val navigationPopBackStackDestinationId: MutableLiveEvent<NavigationPopBackData> = MutableLiveEvent()

    var screenName: String = ""

//    val analyticsManager : AnalyticsManager by lazy {
//        val view = ViewAnalyticsData(
//            screenName = screenName,
//            screenClass = this::class.java.simpleName
//        )
//        AnalyticsManager(view)
//    }

//    override val disposeBag: CompositeDisposable = CompositeDisposable()
//
//    internal fun addDisposableInternal(d: Disposable) {
//        this.disposeBag.add(d)
//    }

//    override fun onCleared() {
//        super.onCleared()
//        this.disposeBag.clear()
//    }

//    fun onAlertDialog(dialog: AlertDialog) {
//        alertDialog.emit(dialog)
//    }

//    fun <T>onNavigate(destinationId: Int, data: (T?)) {
//        val bundle = bundleOf(DATA_CONTEXT_KEY to data)
//        navigationEvent.emit(BaseNavigationData(destinationId, bundle))
//    }
//
//    fun onNavigate(destinationId: Int) {
//        onNavigate(destinationId, null)
//    }
//
//    fun onNavigatePopBackStack(isPopBackStack: Boolean) {
//        navigationPopBackStack.emit(isPopBackStack)
//    }
//
//    fun onNavigatePopBackStack(destinationId: Int, inclusive: Boolean) {
//        val event = NavigationPopBackData(
//            destinationId = destinationId,
//            inclusive = inclusive
//        )
//        navigationPopBackStackDestinationId.emit(event)
//    }

    companion object {
        const val DATA_CONTEXT_KEY = "dataContext"
    }

}
