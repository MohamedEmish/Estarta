package com.example.feature.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.base.BaseFragment
import com.example.common.isOffline
import com.example.feature.databinding.FragmentMainBinding
import com.example.feature.ui.MainActivity
import com.example.feature.ui.MainViewModel
import com.example.feature.ui.contract.MainContract
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Main Fragment
 */
@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by activityViewModels()

    private val adapter: ProductsAdapter by lazy {
        ProductsAdapter { product ->
            viewModel.setEvent(
                MainContract.Event.OnFetchProductDetails(
                    product?.uid ?: return@ProductsAdapter
                )
            )
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(product)
            (activity as MainActivity).navController.navigate(action)
        }
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.rvProducts.adapter = adapter
        initObservers()
        getProductsList()
    }

    private fun getProductsList() =
        viewModel.setEvent(MainContract.Event.OnFetchProductsList)

    /**
     * Initialize Observers
     */
    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (val state = it.productState) {
                        is MainContract.ProductState.Idle -> {
                            binding.pbLoading.isVisible = false
                        }
                        is MainContract.ProductState.Loading -> {
                            if (adapter.itemCount == 0)
                                binding.pbLoading.isVisible = true
                        }
                        is MainContract.ProductState.Success -> {
                            val data = state.productsList
                            adapter.submitList(data)
                            binding.pbLoading.isVisible = false
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    when (it) {
                        is MainContract.Effect.ShowError -> {
                            if (requireContext().isOffline()) {
                                Toast.makeText(
                                    requireContext(),
                                    "No internet connection, please try again later",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else {
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
            }
        }
    }
}