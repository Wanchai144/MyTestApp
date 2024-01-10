package com.example.mytestapp.presentation.feature.main

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.data.model.UserEntity
import com.example.mytestapp.databinding.FragmentHomeMainBinding
import com.example.mytestapp.presentation.feature.adapter.AdapterList
import com.example.mytestapp.presentation.feature.base.BaseViewModel
import com.example.mytestapp.presentation.feature.base.NoInternetState
import com.example.mytestapp.presentation.feature.base.Success
import com.example.mytestapp.presentation.feature.base.ViewState
import com.example.mytestapp.presentation.feature.base.snackbar
import com.example.mytestapp.presentation.feature.viewmodel.base.BaseFragment
import com.example.mytestapp.utils.TextWatcherAdapter
import kotlinx.android.synthetic.main.fragment_home_main.btnAdd
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeMainFragment : BaseFragment(){
    private val binding by lazy { FragmentHomeMainBinding.inflate(layoutInflater) }
    private val viewModel: HomeMainViewModel by viewModel()

    private val adapter: AdapterList by lazy {
        AdapterList()
    }

    var listener = TextWatcherAdapter { s ->
        onTextChanged(s)
    }

    private fun onTextChanged(s: String)  = with(viewModel) {
        val btnAddDrawable = btnAdd.background
        if (s.isNotEmpty()) {
            inputText = s
            val tintColor = ContextCompat.getColor(requireContext(), R.color.activeButton)
            DrawableCompat.setTint(btnAddDrawable, tintColor)
            btnAdd.isEnabled = true
        } else {
            inputText = ""
            val tintColor = ContextCompat.getColor(requireContext(), R.color.inactiveButton)
            DrawableCompat.setTint(btnAddDrawable, tintColor)
            btnAdd.isEnabled = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewModel()
        initRecyclerView()
        callbackAdapter()
    }

    private fun initView()  = with(binding){
        textInputEditText.addTextChangedListener(listener)
        btnAdd.setOnClickListener {
            viewModel.addData()
        }
        btnDelete.setOnClickListener {
            viewModel.deleteDataByIds()
        }
    }

    private fun callbackAdapter(){
        adapter.apply {
            onSelectItem = { selectedIds ->
                selectedIds?.let {
                    viewModel.ids = it
                }
            }
        }
    }

    private fun observeViewModel() = with(viewModel){
        data.observe(viewLifecycleOwner, Observer(this@HomeMainFragment::handleInitialLoadDataViewState))
        resultSuccess.observe(viewLifecycleOwner){
              loadData()
              binding.textInputEditText.text?.clear()
        }
    }

    private fun handleInitialLoadDataViewState(viewState: ViewState<List<UserEntity>>) {
        when (viewState) {
            is Success -> {
                viewState.data.let {
                    adapter.loadData(it)
                }
            }
            else -> {
                snackbar("Data not found", requireView())
            }
        }
    }

    private fun initRecyclerView() = with(binding) {
        rvList.layoutManager =
            LinearLayoutManager(rvList.context, RecyclerView.VERTICAL, false)
        rvList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}