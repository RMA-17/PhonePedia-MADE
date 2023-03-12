package com.rmaprojects.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rmaprojects.core.data.source.local.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun favDao(): FavoriteDao
}