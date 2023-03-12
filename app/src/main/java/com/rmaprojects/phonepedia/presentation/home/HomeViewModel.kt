package com.rmaprojects.phonepedia.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rmaprojects.core.data.source.remote.response.ResponseStatus
import com.rmaprojects.core.domain.use_case.PhonePediaUseCases
import com.rmaprojects.phonepedia.presentation.home.event.HomeUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: PhonePediaUseCases
) : ViewModel() {

    private val _allProducts = MutableStateFlow<HomeUiEvent>(HomeUiEvent.Idle)
    val allProducts = _allProducts.asStateFlow().asLiveData()

    private fun getAllProducts() = useCase.getProductUseCase()

    init {
        viewModelScope.launch {
            getAllProducts().collectLatest { status ->
                when (status) {
                    is ResponseStatus.Error -> {
                        _allProducts.emit(HomeUiEvent.Error(status.message))
                    }
                    is ResponseStatus.Loading -> {
                        _allProducts.emit(HomeUiEvent.Loading)
                    }
                    is ResponseStatus.Success -> {
                        _allProducts.emit(HomeUiEvent.Success(status.data))
                    }
                }
            }
        }
    }
}