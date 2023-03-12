package com.rmaprojects.phonepedia.di

import com.rmaprojects.core.domain.use_case.PhonePediaInteractor
import com.rmaprojects.core.domain.use_case.PhonePediaUseCases
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
@Suppress("unused")
abstract class AppModule {

    @Binds
    abstract fun provideUseCases(interactor: PhonePediaInteractor): PhonePediaUseCases
}