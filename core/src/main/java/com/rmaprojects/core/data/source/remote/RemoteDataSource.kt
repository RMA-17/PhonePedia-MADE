package com.rmaprojects.core.data.source.remote

import com.rmaprojects.core.data.source.remote.network.ApiService
import com.rmaprojects.core.data.source.remote.network.Category
import com.rmaprojects.core.data.source.remote.response.all_products.ProductsResponse
import com.rmaprojects.core.data.source.remote.response.categories.CategoryResponse
import com.rmaprojects.core.data.source.remote.response.product_detail.ProductDetailResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAllProducts(): ProductsResponse {
        return apiService.getAllProducts()
    }

    suspend fun searchProduct(
        query: String,
        category: Category
    ): ProductsResponse {
        return apiService.searchProduct(query, category)
    }

    suspend fun getAllCategories(): CategoryResponse {
        return apiService.getAllCategories()
    }

    suspend fun getProductDetail(productId: String): ProductDetailResponse {
        return apiService.getProductDetail(productId)
    }
}