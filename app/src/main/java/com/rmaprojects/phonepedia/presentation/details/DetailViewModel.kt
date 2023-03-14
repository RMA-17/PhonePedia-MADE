package com.rmaprojects.phonepedia.presentation.details

import android.util.Log
import androidx.lifecycle.*
import com.rmaprojects.core.data.source.remote.response.ResponseStatus
import com.rmaprojects.core.domain.model.Favorite
import com.rmaprojects.core.domain.model.ProductItemDetail
import com.rmaprojects.core.domain.use_case.PhonePediaUseCases
import com.rmaprojects.phonepedia.presentation.details.event.DetailProductUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (
    private val useCases: PhonePediaUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _productId: String = savedStateHandle[DetailsFragment.PRODUCT_ID] ?: throw Exception("Product ID missing")
    private val _isProductInserted: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isProductInsertedIntoFavorite = _isProductInserted.asLiveData()

    private val _uiEventFlow = MutableSharedFlow<DetailProductUiEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow().asLiveData()

    fun getProductDetail(): LiveData<ResponseStatus<ProductItemDetail>> {
        return useCases.getProductDetailUseCase(_productId).asLiveData()
    }

    fun checkIfAdded(product: ProductItemDetail) {
        viewModelScope.launch {
            useCases.getFavoriteById(product.productId).collectLatest { favorite ->
                _isProductInserted.emit(favorite?.productId == product.productId)
                Log.e("FAVORITE_PRODUCT_ID", favorite?.productId ?: "")
                Log.e("CURRENT_PRODUCT_ID", _productId)
            }
        }
    }

    fun addToFavorite(product: ProductItemDetail) {
        try {
            viewModelScope.launch {
                if (_isProductInserted.value) {
                    useCases.deleteFavoriteUseCase(
                        Favorite(
                            product.productId,
                            product.productCategory,
                            product.productImages.first(),
                            product.productModel
                        )
                    )
                    _uiEventFlow.emit(DetailProductUiEvent.DeletedFromFavorite)
                    checkIfAdded(product)
                    return@launch
                }
                useCases.insertFavoriteUseCase(product)
                _uiEventFlow.emit(DetailProductUiEvent.SavedToFavorite)
                checkIfAdded(product)
            }
        } catch (e: Exception) {
            Log.d("ADD_FAVORITE", e.toString())
            viewModelScope.launch { _uiEventFlow.emit(DetailProductUiEvent.Error(e.message ?: "Error occurred when inserting favorite")) }
        }
    }
}