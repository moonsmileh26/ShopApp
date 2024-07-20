package mx.com.liverpool.shopapp.products.domain.repository

import kotlinx.coroutines.flow.Flow
import mx.com.liverpool.shopapp.products.domain.model.Product

interface ProductsRepository {
    suspend fun searchProductsByTerm(word: String, page: Int): Flow<List<Product>>
    suspend fun searchProductsByTermSorted(
        word: String,
        page: Int,
        sorted: Boolean
    ): Flow<List<Product>>
}