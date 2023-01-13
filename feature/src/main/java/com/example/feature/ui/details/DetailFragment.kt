package com.example.feature.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.base.BaseFragment
import com.example.feature.databinding.FragmentDetailsBinding
import com.example.feature.model.ProductUiModel
import com.example.feature.ui.MainActivity
import com.example.feature.ui.MainViewModel
import com.example.feature.ui.contract.MainContract
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailsBinding>() {

    private val viewModel: MainViewModel by activityViewModels()

    private val imagesAdapter by lazy {
        ImagesAdapter()
    }
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) = initObservers()

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    setData(it.selectedProduct)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    when (it) {
                        is MainContract.Effect.ShowError -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setData(selectedProduct: ProductUiModel?) {
        with(binding) {
            tvTitle.apply {
                setOnClickListener {
                    (activity as MainActivity).navController.navigateUp()
                }
                text = selectedProduct?.name
            }

            tvPrice.text = selectedProduct?.price
            imagesAdapter.submitList(selectedProduct?.imageUrls ?: mutableListOf())
            vpImages.apply {
                adapter = imagesAdapter
            }
        }
    }
}