package com.rmaprojects.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rmaprojects.core.domain.use_case.PhonePediaUseCases
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val phonePediaUseCases: PhonePediaUseCases
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(phonePediaUseCases) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}