package com.rmaprojects.core.domain.repository

import com.rmaprojects.core.data.source.remote.response.ResponseStatus
import com.rmaprojects.core.domain.model.CategoryEntity
import com.rmaprojects.core.domain.model.Favorite
import com.rmaprojects.core.domain.model.ProductItemDetail
import com.rmaprojects.core.domain.model.ProductItemList
import kotlinx.coroutines.flow.Flow

interface PhonePediaRepository {
    fun getAllProduct(): Flow<ResponseStatus<List<ProductItemList>>>
    fun getAllCategory(): Flow<ResponseStatus<CategoryEntity>>
    fun searchProduct(query: String, category: String): Flow<ResponseStatus<List<ProductItemList>>>
    fun getProductDetail(productId: String): Flow<ResponseStatus<ProductItemDetail>>
    suspend fun insertFavorite(product: ProductItemDetail)
    suspend fun deleteFavorite(favorite: Favorite)
    fun getAllFavorites(): Flow<List<Favorite>>
    suspend fun getFavoriteDetails(productId: String): Favorite?
}