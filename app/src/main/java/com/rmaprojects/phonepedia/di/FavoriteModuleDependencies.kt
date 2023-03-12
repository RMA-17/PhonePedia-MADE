package com.rmaprojects.phonepedia.di

import com.rmaprojects.core.domain.use_case.PhonePediaUseCases
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun phonePediaUseCases(): PhonePediaUseCases
}