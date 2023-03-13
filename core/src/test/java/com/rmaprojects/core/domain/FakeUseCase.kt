package com.rmaprojects.core.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.rmaprojects.core.data.DummyData
import com.rmaprojects.core.data.source.remote.response.ResponseStatus
import com.rmaprojects.core.domain.model.CategoryEntity
import com.rmaprojects.core.domain.model.Favorite
import com.rmaprojects.core.domain.model.ProductItemDetail
import com.rmaprojects.core.domain.model.ProductItemList
import com.rmaprojects.core.domain.use_case.PhonePediaUseCases
import com.rmaprojects.core.utils.asFavorite
import com.rmaprojects.core.utils.mapIntoFavorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUseCase: PhonePediaUseCases {

    private val listProductItem = DummyData.generateDummyItemList()
    private val productDetail = DummyData.generateDummyItemDetail()
    private val favoriteItemList = mutableListOf<Favorite>()

    override fun getProductUseCase(): Flow<ResponseStatus<List<ProductItemList>>> {
        return flow {
            emit(ResponseStatus.Success(listProductItem))
        }
    }

    override fun searchProductUseCase(
        query: String,
        category: String
    ): Flow<ResponseStatus<List<ProductItemList>>> {
        return flow {
            emit(ResponseStatus.Success(listProductItem.filter { it.productModel == query }))
        }
    }

    override fun getCategoriesUseCase(): Flow<ResponseStatus<CategoryEntity>> {
        return flow {
            emit(ResponseStatus.Success(CategoryEntity(listOf("Smartphones", "Tablets", "Laptops"))))
        }
    }

    override fun getProductDetailUseCase(productId: String): Flow<ResponseStatus<ProductItemDetail>> {
        return flow {
            emit(ResponseStatus.Success(productDetail.takeIf { it.productId == productId }!!))
        }
    }

    override fun getFavoriteUseCase(): Flow<List<Favorite>> {
        val observeAbleFavorite = MutableLiveData<List<Favorite>>()
        observeAbleFavorite.value = favoriteItemList
        return observeAbleFavorite.asFlow()
    }

    override suspend fun deleteFavoriteUseCase(favorite: Favorite) {
        favoriteItemList.remove(favorite)
    }

    override suspend fun insertFavoriteUseCase(product: ProductItemDetail) {
        val mappedProduct = product.mapIntoFavorite()
        favoriteItemList.add(mappedProduct.asFavorite())
    }

    override suspend fun validateIsFavoriteUseCase(productId: String): Boolean {
        return favoriteItemList.any { it.productId == productId }
    }
}