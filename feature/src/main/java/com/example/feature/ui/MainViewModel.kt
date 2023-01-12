package com.example.feature.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.common.Mapper
import com.example.common.Resource
import com.example.common.addIfNotExist
import com.example.domain.entity.ProductEntity
import com.example.domain.useCase.GetProductsListUseCase
import com.example.feature.model.ProductUiModel
import com.example.feature.ui.contract.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getProductsListUseCase: GetProductsListUseCase,
    private val productMapper: Mapper<ProductEntity, ProductUiModel>
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    private val listOfProducts: MutableList<ProductUiModel> = mutableListOf()

    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            productState = MainContract.ProductState.Idle,
            selectedProduct = null
        )
    }

    init {
        fetchProductsList()
    }

    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnFetchProductsList -> fetchProductsList()
            is MainContract.Event.OnFetchProductDetails -> {
                val item = event.uId
                setSelectedProduct(product = listOfProducts.find { product -> product.uid == item })
            }
        }
    }

    /**
     * Fetch products
     */
    private fun fetchProductsList() =
        viewModelScope.launch {
            getProductsListUseCase.execute()
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(productState = MainContract.ProductState.Loading) }
                        }
                        is Resource.Empty -> {
                            setState { copy(productState = MainContract.ProductState.Idle) }
                        }
                        is Resource.Error -> {
                            setEffect { MainContract.Effect.ShowError(message = it.exception.message) }
                        }
                        is Resource.Success -> {
                            val productsList = productMapper.fromList(it.data)
                            productsList.forEach { product ->
                                listOfProducts.addIfNotExist(product)
                            }

                            setState {
                                copy(
                                    productState = MainContract.ProductState.Success(
                                        productsList = listOfProducts.toList()
                                    )
                                )
                            }
                        }
                    }
                }
        }


    /**
     * Set selected product item
     */
    private fun setSelectedProduct(product: ProductUiModel?) {
        // Set State
        setState { copy(selectedProduct = product) }
    }
}