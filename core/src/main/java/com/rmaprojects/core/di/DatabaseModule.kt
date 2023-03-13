package com.rmaprojects.core.di

import android.app.Application
import androidx.room.Room
import com.rmaprojects.core.data.source.local.database.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(
        context: Application
    ): FavoriteDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("phonePedia".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context.applicationContext,
            FavoriteDatabase::class.java,
            "favorite.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}