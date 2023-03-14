package com.rmaprojects.core.data.repository

import android.util.Log
import com.rmaprojects.core.data.source.local.LocalDataSource
import com.rmaprojects.core.data.source.remote.RemoteDataSource
import com.rmaprojects.core.data.source.remote.response.ResponseStatus
import com.rmaprojects.core.domain.model.Category
import com.rmaprojects.core.domain.model.Favorite
import com.rmaprojects.core.domain.model.ProductItemDetail
import com.rmaprojects.core.domain.model.ProductItemList
import com.rmaprojects.core.domain.repository.PhonePediaRepository
import com.rmaprojects.core.utils.asFavorite
import com.rmaprojects.core.utils.asFavoriteEntity
import com.rmaprojects.core.utils.mapIntoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import com.rmaprojects.core.data.source.remote.network.Category as CategoryBody

@Singleton
class PhonePediaRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : PhonePediaRepository {

    override fun getAllProduct(): Flow<ResponseStatus<List<ProductItemList>>> {
        return flow {
            emit(ResponseStatus.Loading)
            try {
                val response = remoteDataSource.getAllProducts()
                if (response.status == 400) {
                    emit(ResponseStatus.Error(response.message))
                    return@flow
                }
                val entityItems = response.data.items.map {
                    it.mapIntoEntity()
                }
                emit(ResponseStatus.Success(entityItems))
            } catch (e: Exception) {
                Log.d("GET_ALL_PRODUCTS", e.toString())
                emit(ResponseStatus.Error(e.message ?: "Error Occurred when retrieving data"))
            }
        }
    }

    override fun getAllCategory(): Flow<ResponseStatus<Category>> {
        return flow {
            emit(ResponseStatus.Loading)
            try {
                val response = remoteDataSource.getAllCategories()
                if (response.status == 400) {
                    emit(ResponseStatus.Error(response.message))
                    return@flow
                }

                val categories = response.data.items.map {
                    it.name
                }

                val category = Category(categories)

                emit(ResponseStatus.Success(category))
            } catch (e: Exception) {
                Log.d("GET_CATEGORIES", e.toString())
                emit(ResponseStatus.Error(e.message ?: "Error Occurred when retrieving data"))
            }
        }
    }

    override fun searchProduct(
        query: String,
        category: String
    ): Flow<ResponseStatus<List<ProductItemList>>> {
        return flow {
            emit(ResponseStatus.Loading)
            try {
                val categoryConvert = CategoryBody(category)
                val response = remoteDataSource.searchProduct(
                    query = query,
                    category = categoryConvert
                )
                if (response.status == 400) {
                    emit(ResponseStatus.Error(response.message))
                    return@flow
                }

                val entityItems = response.data.items.map {
                    it.mapIntoEntity()
                }

                emit(ResponseStatus.Success(entityItems))
            } catch (e: Exception) {
                Log.d("SEARCH_PRODUCT", e.toString())
                emit(ResponseStatus.Error(e.message ?: "Error Occurred when retrieving data"))
            }
        }
    }

    override fun getProductDetail(productId: String): Flow<ResponseStatus<ProductItemDetail>> {
        return flow {
            emit(ResponseStatus.Loading)
            try {
                val response = remoteDataSource.getProductDetail(productId)
                if (response.status == 400) {
                    emit(ResponseStatus.Error(response.message))
                    return@flow
                }

                val entityItems = response.data.items.map {
                    it.mapIntoEntity()
                }

                emit(ResponseStatus.Success(entityItems.last()))
            } catch (e: Exception) {
                Log.d("GET_PRODUCT_DETAIL", e.toString())
                emit(ResponseStatus.Error(e.message ?: "Error Occurred when retrieving data"))
            }
        }
    }

    override suspend fun insertFavorite(product: ProductItemDetail) {
        return localDataSource.insertFavorite(product)
    }

    override suspend fun deleteFavorite(favorite: Favorite) {
        return localDataSource.deleteFavorite(favorite.asFavoriteEntity())
    }

    override fun getAllFavorites(): Flow<List<Favorite>> {
        return localDataSource.getAllFavorite().map { listRawFavorite ->
            listRawFavorite.map { rawFavorite ->
                rawFavorite.asFavorite()
            }
        }
    }

    override suspend fun getFavoriteDetails(productId: String): Favorite? {
        return localDataSource.getProductDetail(productId)?.asFavorite()
    }
}