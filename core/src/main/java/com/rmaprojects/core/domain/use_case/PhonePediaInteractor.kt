package com.rmaprojects.core.domain.use_case

import com.rmaprojects.core.data.source.remote.response.ResponseStatus
import com.rmaprojects.core.domain.model.Category
import com.rmaprojects.core.domain.model.Favorite
import com.rmaprojects.core.domain.model.ProductItemDetail
import com.rmaprojects.core.domain.model.ProductItemList
import com.rmaprojects.core.domain.repository.PhonePediaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhonePediaInteractor @Inject constructor(
    private val repository: PhonePediaRepository
): PhonePediaUseCases {

    override fun getProductUseCase(): Flow<ResponseStatus<List<ProductItemList>>> {
        return repository.getAllProduct()
    }

    override fun searchProductUseCase(
        query: String,
        category: String
    ): Flow<ResponseStatus<List<ProductItemList>>> {
        return repository.searchProduct(query, category)
    }

    override fun getCategoriesUseCase(): Flow<ResponseStatus<Category>> {
        return repository.getAllCategory()
    }

    override fun getProductDetailUseCase(productId: String): Flow<ResponseStatus<ProductItemDetail>> {
        return repository.getProductDetail(productId)
    }

    override fun getFavoriteUseCase(): Flow<List<Favorite>> {
        return repository.getAllFavorites()
    }

    override suspend fun deleteFavoriteUseCase(favorite: Favorite) {
        repository.deleteFavorite(favorite)
    }

    override suspend fun insertFavoriteUseCase(product: ProductItemDetail) {
        repository.insertFavorite(product)
    }

    override fun getFavoriteById(productId: String): Flow<Favorite?> {
        return repository.getFavoriteDetails(productId)
    }
}