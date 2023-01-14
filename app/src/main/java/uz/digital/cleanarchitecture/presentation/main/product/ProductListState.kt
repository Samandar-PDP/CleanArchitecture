package uz.digital.cleanarchitecture.presentation.main.product

import uz.digital.cleanarchitecture.domain.model.Product

sealed class ProductListState {
    object Idle : ProductListState()
    object Loading : ProductListState()
    data class Error(val message: String) : ProductListState()
    data class Success(val productList: List<Product>) : ProductListState()
}