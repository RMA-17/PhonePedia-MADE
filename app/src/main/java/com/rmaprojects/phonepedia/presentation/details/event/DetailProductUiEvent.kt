package com.rmaprojects.phonepedia.presentation.details.event

sealed class DetailProductUiEvent {
    object SavedToFavorite: DetailProductUiEvent()
    object DeletedFromFavorite: DetailProductUiEvent()
    data class Error(val message: String): DetailProductUiEvent()
}
