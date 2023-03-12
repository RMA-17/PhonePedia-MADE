package com.rmaprojects.favorite.di

import android.content.Context
import com.rmaprojects.favorite.presentation.FavoriteFragment
import com.rmaprojects.phonepedia.di.FavoriteModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [FavoriteModuleDependencies::class]
)
interface FavoriteComponent {
    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}