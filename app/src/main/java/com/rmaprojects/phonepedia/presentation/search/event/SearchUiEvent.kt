package com.rmaprojects.phonepedia.presentation.search.event

sealed class SearchUiEvent {
    data class Error(val message: String): SearchUiEvent()
    object Loading: SearchUiEvent()
    object EmptyQuery: SearchUiEvent()
    object Success: SearchUiEvent()
}
