package com.rmaprojects.core.domain.use_case

import com.rmaprojects.core.data.source.remote.response.ResponseStatus
import com.rmaprojects.core.data.source.remote.response.categories.CategoryResponse
import com.rmaprojects.core.data.source.local.entity.FavoriteEntity
import com.rmaprojects.core.domain.model.CategoryEntity
import com.rmaprojects.core.domain.model.Favorite
import com.rmaprojects.core.domain.model.ProductItemDetail
import com.rmaprojects.core.domain.model.ProductItemList
import kotlinx.coroutines.flow.Flow

interface PhonePediaUseCases {
    fun getProductUseCase(): Flow<ResponseStatus<List<ProductItemList>>>
    fun searchProductUseCase(query: String, category: String = "Smartphones"): Flow<ResponseStatus<List<ProductItemList>>>
    fun getCategoriesUseCase(): Flow<ResponseStatus<CategoryEntity>>
    fun getProductDetailUseCase(productId: String): Flow<ResponseStatus<ProductItemDetail>>
    fun getFavoriteUseCase(): Flow<List<Favorite>>
    suspend fun deleteFavoriteUseCase(favorite: Favorite)
    suspend fun insertFavoriteUseCase(product: ProductItemDetail)
    suspend fun validateIsFavoriteUseCase(productId: String): Boolean
}