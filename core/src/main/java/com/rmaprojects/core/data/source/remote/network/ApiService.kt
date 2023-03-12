package com.rmaprojects.core.data.source.remote.network

import com.rmaprojects.core.data.source.remote.response.all_products.ProductsResponse
import com.rmaprojects.core.data.source.remote.response.categories.CategoryResponse
import com.rmaprojects.core.data.source.remote.response.product_detail.ProductDetailResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("/v4/product/search")
    suspend fun searchProduct(
        @Query("query") query: String,
        @Body category: Category
    ): ProductsResponse

    @POST("/v4/all/products")
    suspend fun getAllProducts(
        @Query("page") page: Int = 1,
        @Body brand: Array<String>? = arrayOf(),
    ): ProductsResponse

    @GET("/v4/product/detail")
    suspend fun getProductDetail(
        @Query("productId") id: String
    ): ProductDetailResponse

    @GET("/v4/all/categories")
    suspend fun getAllCategories(): CategoryResponse
}

data class Category(
    val category: String
)