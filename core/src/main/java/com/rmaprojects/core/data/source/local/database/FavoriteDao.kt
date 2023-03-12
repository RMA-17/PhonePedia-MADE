package com.rmaprojects.core.data.source.local.database

import androidx.room.*
import com.rmaprojects.core.data.source.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(entity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(entity: FavoriteEntity)

    @Query("SELECT * FROM tbl_favorite")
    fun getAllFavorite(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM tbl_favorite WHERE id = :productId")
    suspend fun getFavProductDetail(productId: String): FavoriteEntity?
}