package com.rmaprojects.phonepedia.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rmaprojects.core.data.source.remote.response.ResponseStatus
import com.rmaprojects.core.domain.model.ProductItemList
import com.rmaprojects.core.domain.use_case.PhonePediaUseCases
import com.rmaprojects.phonepedia.presentation.search.event.SearchEvent
import com.rmaprojects.phonepedia.presentation.search.event.SearchUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: PhonePediaUseCases
) : ViewModel() {

    private val _typedQuery = MutableStateFlow("")

    private val _uiEvent = MutableStateFlow<SearchUiEvent>(SearchUiEvent.EmptyQuery)
    val uiEvent = _uiEvent.asLiveData()

    private val _productList = MutableStateFlow<List<ProductItemList>>(emptyList())
    val productList = _productList.asLiveData()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.TypingQueries -> {
                viewModelScope.launch { _typedQuery.emit(event.query) }
            }
            is SearchEvent.BeginSearch -> {
                viewModelScope.launch {
                    if (_typedQuery.value.isBlank()) {
                        _uiEvent.emit(SearchUiEvent.EmptyQuery)
                        return@launch
                    }
                    searchProduct()
                }
            }
            is SearchEvent.ChangeCategory -> {
                val category = event.category.filterValue
                viewModelScope.launch {
                    if (_typedQuery.value.isBlank()) {
                        _uiEvent.emit(SearchUiEvent.EmptyQuery)
                        return@launch
                    }
                    searchProduct(category)
                }
            }
        }
    }

    private suspend fun searchProduct(
        category: String = "Smartphones"
    ) {
        Log.d("CATEGORY", category)
        useCases.searchProductUseCase(_typedQuery.value, category).collectLatest { status ->
            when (status) {
                is ResponseStatus.Error -> {
                    _uiEvent.emit(SearchUiEvent.Error(status.message))
                }
                is ResponseStatus.Loading -> {
                    _uiEvent.emit(SearchUiEvent.Loading)
                }
                is ResponseStatus.Success -> {
                    _uiEvent.emit(SearchUiEvent.Success)
                    _productList.value = status.data
                }
            }
        }
    }

}