package com.example.feature.ui.contract

import com.example.base.UiEffect
import com.example.base.UiEvent
import com.example.base.UiState
import com.example.feature.model.ProductUiModel

/**
 * Contract of Main Screen
 */
class MainContract {

    sealed class Event : UiEvent {
        object OnFetchProductsList : Event()
        data class OnFetchProductDetails(val uId: String) : Event()
    }

    data class State(
        val productState: ProductState,
        val selectedProduct: ProductUiModel? = null
    ) : UiState

    sealed class ProductState {
        object Idle : ProductState()
        object Loading : ProductState()
        data class Success(val productsList: List<ProductUiModel>) : ProductState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message: String?) : Effect()
    }
}