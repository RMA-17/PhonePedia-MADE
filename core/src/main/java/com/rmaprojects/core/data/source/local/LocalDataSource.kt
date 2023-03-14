package com.rmaprojects.core.data.source.local

import com.rmaprojects.core.data.source.local.database.FavoriteDatabase
import com.rmaprojects.core.data.source.local.entity.FavoriteEntity
import com.rmaprojects.core.domain.model.ProductItemDetail
import com.rmaprojects.core.utils.mapIntoFavorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val database: FavoriteDatabase
) {
    suspend fun insertFavorite(product: ProductItemDetail) {
        return database.favDao().insertFavorite(product.mapIntoFavorite())
    }

    suspend fun deleteFavorite(entity: FavoriteEntity) {
        return database.favDao().deleteFavorite(entity)
    }

    fun getAllFavorite(): Flow<List<FavoriteEntity>> {
        return database.favDao().getAllFavorite()
    }

    fun getProductDetail(productId: String): Flow<FavoriteEntity?> {
        return database.favDao().getFavProductDetail(productId)
    }
}