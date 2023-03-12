package com.rmaprojects.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_favorite")
data class FavoriteEntity(
    @PrimaryKey val id: String,
    val productCategory: String?,
    val productImage: String?,
    val productName: String?,
)
