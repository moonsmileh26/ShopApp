package mx.com.liverpool.shopapp.products.data.network

import mx.com.liverpool.shopapp.BuildConfig
import mx.com.liverpool.shopapp.products.data.network.dto.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApiService {
    @GET(BuildConfig.ENDPOINT)
    suspend fun searchProductsByTerm(
        @Query("search-string") term: String,
        @Query("page-number") page: Int
    ): Response

    @GET(BuildConfig.ENDPOINT)
    suspend fun searchProductsByTermSorted(
        @Query("search-string") term: String,
        @Query("page-number") page: Int,
        @Query("minSortPrice") sortPrice: Int
    ): Response
}