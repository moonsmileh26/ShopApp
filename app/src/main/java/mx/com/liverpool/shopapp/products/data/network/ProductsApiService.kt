package mx.com.liverpool.shopapp.products.data.network

import mx.com.liverpool.shopapp.products.data.network.dto.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApiService {
    @GET("appclienteservices/services/v3/plp?")
    suspend fun searchProductsByTerm(
        @Query("search-string") term: String,
        @Query("page-number") page: Int
    ): Response
}