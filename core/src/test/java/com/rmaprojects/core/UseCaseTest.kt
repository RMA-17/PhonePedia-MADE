package com.rmaprojects.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.rmaprojects.core.data.DummyData
import com.rmaprojects.core.data.source.remote.response.ResponseStatus
import com.rmaprojects.core.domain.FakeUseCase
import com.rmaprojects.core.domain.model.Category
import com.rmaprojects.core.domain.model.ProductItemList
import com.rmaprojects.core.utils.MainDispatcherRule
import com.rmaprojects.core.utils.observeForTesting
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class UseCaseTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    private lateinit var fakeUseCase: FakeUseCase

    @Before
    fun setUp() {
        fakeUseCase = FakeUseCase()
    }

    @Test
    fun `getProductUseCase must not be null`() = runTest {
        val expectedProducts = DummyData.generateDummyItemList()
        val actualProducts = fakeUseCase.getProductUseCase().asLiveData()
        actualProducts.observeForTesting {
            assertNotNull(actualProducts)
            assertEquals(
                expectedProducts.size,
                (actualProducts.value as ResponseStatus.Success<List<ProductItemList>>).data.size
            )
        }
    }

    @Test
    fun `searchProductUseCase must no be null`() = runTest {
        val query = "SM-20200"
        val expectedProducts = DummyData.generateDummyItemList().filter { it.productModel == query }
        val actualProducts = fakeUseCase.searchProductUseCase(query).asLiveData()
        actualProducts.observeForTesting {
            assertNotNull(actualProducts)
            assertEquals(
                expectedProducts[0].productModel,
                (actualProducts.value as ResponseStatus.Success<List<ProductItemList>>).data[0].productModel
            )
        }
    }

    @Test
    fun `getCategories must return 3 categories`() = runTest {
        val fakeCategories = fakeUseCase.getCategoriesUseCase().asLiveData()
        fakeCategories.observeForTesting {
            assertNotNull(fakeCategories)
            assertEquals(
                listOf("Smartphones", "Tablets", "Laptops"),
                (fakeCategories.value as ResponseStatus.Success<Category>).data.categories
            )
        }
    }

    @Test
    fun `getProductDetailUseCase must provide detail`() = runTest {
        val productId = "2"
        val fakeDetail = fakeUseCase.getProductDetailUseCase(productId).asLiveData()
        fakeDetail.observeForTesting {
            assertNotNull(fakeDetail)
            assertEquals(
                productId,
                (fakeDetail.value as ResponseStatus.Success).data.productId
            )
        }
    }

}