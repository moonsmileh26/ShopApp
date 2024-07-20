package mx.com.liverpool.shopapp.products.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import mx.com.liverpool.shopapp.products.domain.model.Product
import mx.com.liverpool.shopapp.products.domain.repository.ProductsRepository

class SearchProductsUseCase(private val repository: ProductsRepository) {
    private var currentPage = 1

    suspend operator fun invoke(word: String = ""): Flow<List<Product>> {
        return withContext(Dispatchers.IO) {
            repository.searchProductsByTerm(word, currentPage++)
        }
    }
}