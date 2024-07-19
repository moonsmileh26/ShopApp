package mx.com.liverpool.shopapp.products.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.liverpool.shopapp.products.data.network.ProductsApiService
import mx.com.liverpool.shopapp.products.data.repository.ProductsRepositoryImpl
import mx.com.liverpool.shopapp.products.domain.repository.ProductsRepository
import mx.com.liverpool.shopapp.products.domain.usecase.SearchProductsUseCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ProductsModule {
    @Singleton
    @Provides
    fun providesProductsRepository(
        apiService: ProductsApiService
    ): ProductsRepository {
        return ProductsRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun providesSearchProductUseCases(repository: ProductsRepository): SearchProductsUseCase {
        return SearchProductsUseCase(repository)
    }
}