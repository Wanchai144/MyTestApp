package com.example.mytestapp.presentation.feature.viewmodel.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestapp.presentation.extension.statusBarTransparentManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import androidx.navigation.fragment.findNavController


abstract class BaseFragment : Fragment(), CoroutineScope {

    protected open var isTransparent: Boolean = false

    protected open var screenName: String = ""

//    override val disposeBag by lazy { CompositeDisposable() }

    private val job = Job()

    override val coroutineContext = job + Dispatchers.Main

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //screenName = this::class.java.simpleName
    }

    override fun onStart() {
        super.onStart()
        requireActivity().statusBarTransparentManager(isTransparent)
    }

    override fun onStop() {
        super.onStop()
        if (isTransparent) {
            requireActivity().statusBarTransparentManager(!isTransparent)
        }
    }

    fun navigationEvent(navigationData: BaseNavigationData) {
        findNavController().navigate(navigationData.destinationId, navigationData.data)
    }

}