package mx.com.liverpool.shopapp.products.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.com.liverpool.shopapp.products.domain.usecase.SearchProductsUseCase
import mx.com.liverpool.shopapp.products.presentation.ProductsState
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val useCase: SearchProductsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProductsState())
    val state: StateFlow<ProductsState> = _state

    init {
        viewModelScope.launch {
            _state.value = ProductsState.Loading(true)

            useCase().collect { products ->
                if (products.isEmpty()) {
                    _state.value = ProductsState.Success(products)
                } else {
                    _state.value = ProductsState.Error(Error())
                }
                _state.value = ProductsState.Loading(false)
            }

        }
    }
}