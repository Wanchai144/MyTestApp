package com.example.mytestapp.presentation.feature.viewmodel.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class DataBindingFragment<Binding : ViewDataBinding, VM : BaseViewModel> : BaseFragment() {

    private var _binding: Binding? = null

    protected val binding: Binding get() = _binding!!

    protected lateinit var vm: VM

//    protected open val analyticsManager : AnalyticsManager by lazy {
//        val view = ViewAnalyticsData(
//            screenName = screenName,
//            screenClass = this::class.java.simpleName
//        )
//        AnalyticsManager(view)
//    }

    protected abstract fun getLayoutResId(): Int

    abstract fun createViewModel(savedInstanceState: Bundle?): VM

    protected abstract fun onCreateViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = createViewModel(savedInstanceState)
//        subscribeToViewModel(vm)
        onCreateViewModel()
    }

//    private fun subscribeToViewModel(viewModel: BaseViewModel) {
//        //Analytics
//        if(screenName.isNotEmpty()){
//            viewModel.screenName = screenName
//            analyticsManager.view()
//            Log.d("BaseFragment","viewAnalyticsData $screenName")
//        }
//        viewModel.fitsSystemWindows.value = !isTransparent
//        viewModel.alertDialog.observe(viewLifecycleOwner, this::onAlert)
//        viewModel.navigationEvent.observe(viewLifecycleOwner, this::navigationEvent)
//        viewModel.navigationPopBackStack.observe(viewLifecycleOwner, this::navigationPopBackStack)
//        viewModel.navigationPopBackStackDestinationId.observe(viewLifecycleOwner, this::navigationPopBackStackDestinationId)
//    }
//
//    private fun onAlert(alertDialog: AlertDialog) {
//        alertDialog.show(childFragmentManager, "${screenName}_alert_dialog")
//    }
//
//    private fun navigationEvent(navigationData: BaseNavigationData) {
//        findNavController().navigate(navigationData.destinationId, navigationData.data)
//    }
//
//    private fun navigationPopBackStack(isPopBackStack: Boolean) {
//        if (isPopBackStack) {
//            findNavController().popBackStack()
//        }
//    }
//
//    private fun navigationPopBackStackDestinationId(navigationData: NavigationPopBackData){
//        findNavController().popBackStack(navigationData.destinationId, navigationData.inclusive)
//    }

    companion object {
        const val DATA_CONTEXT_KEY = "dataContext"
    }

}