package com.rmaprojects.phonepedia.presentation.home.event

import com.rmaprojects.core.domain.model.ProductItemList

sealed class HomeUiEvent {
    object Idle: HomeUiEvent()
    data class Success(val data: List<ProductItemList>): HomeUiEvent()
    object Loading: HomeUiEvent()
    data class Error(val message: String): HomeUiEvent()
}