package com.rmaprojects.core.di

import com.rmaprojects.core.data.repository.PhonePediaRepositoryImpl
import com.rmaprojects.core.domain.repository.PhonePediaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
@Suppress("unused")
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(phonePediaRepository: PhonePediaRepositoryImpl): PhonePediaRepository
}