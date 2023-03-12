package com.rmaprojects.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rmaprojects.core.domain.model.Favorite
import com.rmaprojects.core.domain.use_case.PhonePediaUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteViewModel (
    private val phonePediaUseCases: PhonePediaUseCases
): ViewModel() {

    private val _favProductList = MutableStateFlow<List<Favorite>>(emptyList())
    val favProductList = _favProductList.asStateFlow().asLiveData()

    private fun getAllFavProducts() {
        viewModelScope.launch {
            phonePediaUseCases.getFavoriteUseCase().collectLatest { list ->
                _favProductList.emit(list)
            }
        }
    }

    init {
        getAllFavProducts()
    }
}