package mx.com.liverpool.shopapp.products.presentation

import mx.com.liverpool.shopapp.products.domain.model.Product

open class ProductsState {
    data class Loading(val isLoading: Boolean) : ProductsState()
    data class Error(val Error: kotlin.Error) : ProductsState()
    data class Success(val products: List<Product>) : ProductsState()
}