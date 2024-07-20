package mx.com.liverpool.shopapp.products.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.com.liverpool.shopapp.products.domain.model.Product
import mx.com.liverpool.shopapp.products.domain.usecase.SearchProductsUseCase
import mx.com.liverpool.shopapp.products.domain.usecase.SortProductsUseCase
import mx.com.liverpool.shopapp.products.presentation.ProductsState
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val useCase: SearchProductsUseCase,
    private val sortProductsUseCase: SortProductsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProductsState())
    val state: StateFlow<ProductsState> = _state

    init {
        viewModelScope.launch {
            _state.value = ProductsState.Loading(true)

            useCase().collect { products ->
                if (products.isEmpty()) {
                    _state.value = ProductsState.Error(Error())
                } else {
                    _state.value = ProductsState.Success(handleProducts(products))
                }
                _state.value = ProductsState.Loading(false)
            }
        }
    }

    fun searchProductsByTerm(word: String) {
        viewModelScope.launch {
            _state.value = ProductsState.Loading(true)

            useCase(word).collect { products ->
                if (products.isEmpty()) {
                    _state.value = ProductsState.Error(Error())
                } else {
                    _state.value = ProductsState.Success(handleProducts(products))
                }
                _state.value = ProductsState.Loading(false)
            }
        }
    }

    fun sortProducts(word: String, sortType: Int) {
        viewModelScope.launch {
            _state.value = ProductsState.Loading(true)
            val sort = sortType.equals(2)
            sortProductsUseCase(word, sort).collect { products ->
                if (products.isEmpty()) {
                    _state.value = ProductsState.Error(Error())
                } else {
                    _state.value = ProductsState.SuccessSorted(handleSortedProducts(products, sort))
                }
                _state.value = ProductsState.Loading(false)
            }
        }
    }

    private fun handleProducts(products: List<Product>): List<Product> {
        return products.map {
            it.copy(
                it.image,
                it.name,
                listPrice = formatPrice(it.listPrice.toDouble()),
                promoPrice = formatPrice(it.promoPrice.toDouble()),
                it.colors
            ).toProduct()
        }
    }

    private fun handleSortedProducts(
        products: List<Product>,
        isDescending: Boolean
    ): List<Product> {
        val preProducts = products.map {
            it.copy(
                it.image,
                it.name,
                listPrice = formatPrice(it.listPrice.toDouble()),
                promoPrice = formatPrice(it.promoPrice.toDouble()),
                it.colors
            ).toProduct()
        }
        return if (isDescending) preProducts.sortedByDescending { it.promoPrice } else
            preProducts.sortedBy { it.promoPrice }
    }

    private fun formatPrice(price: Double): String {
        val df = DecimalFormat("#,##0.00", DecimalFormatSymbols(Locale.ENGLISH))
        return df.format(price)

    }
}