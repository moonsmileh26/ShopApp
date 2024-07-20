package mx.com.liverpool.shopapp.products.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.com.liverpool.shopapp.products.data.network.ProductsApiService
import mx.com.liverpool.shopapp.products.domain.model.Product
import mx.com.liverpool.shopapp.products.domain.repository.ProductsRepository

class ProductsRepositoryImpl(private val api: ProductsApiService) : ProductsRepository {
    override suspend fun searchProductsByTerm(word: String, page: Int): Flow<List<Product>> {
        return flow {
            try {
                val apiResponse = api.searchProductsByTerm(word, page)
                val products = apiResponse.plpResults.records.map {
                    it.toDomain()
                }
                emit(products)
            } catch (e: Exception) {
                emit(listOf())
                e.printStackTrace()
            }
        }
    }

    override suspend fun searchProductsByTermSorted(
        word: String,
        page: Int,
        sorted: Boolean
    ): Flow<List<Product>> {
        return flow {
            try {
                val sort = if (sorted) 1 else 0
                val apiResponse = api.searchProductsByTermSorted(word, page, sort)
                val products = apiResponse.plpResults.records.map {
                    it.toDomain()
                }
                emit(products)
            } catch (e: Exception) {
                emit(listOf())
                e.printStackTrace()
            }
        }
    }
}