package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * JCP-05: Практика написания тестов — ViewModel продуктов
 *
 * Задание: написать unit-тесты для ProductsViewModel
 * в src/test/java/.../tasks/jcp05/ProductsViewModelTest.kt.
 *
 * ViewModel уже реализована. Она управляет ProductsUiState через StateFlow
 * и принимает ProductsRepository в конструктор (для тестов подставляется MockK-стаб).
 *
 * Что делает ViewModel:
 * 1. loadProducts() — загружает продукты из репозитория и обновляет uiState
 * 2. onSearchChanged(query) — фильтрует продукты по названию
 * 3. addToFavorites(productId) — добавляет/убирает продукт в избранное
 *
 * Что нужно протестировать:
 * 1. Начальное состояние — isLoading = true, продукты пустые
 * 2. Успешная загрузка — isLoading = false, список продуктов не пуст
 * 3. Ошибка загрузки — isLoading = false, error не null
 * 4. Фильтрация по поиску — в списке остаются только продукты, чьё название содержит запрос
 * 5. Добавление в избранное — favourites обновляется сразу, без повторной загрузки
 *
 * ПРИМЕР из урока RCA-49: Тест ViewModel с Turbine
 *
 * @Test
 * fun `loads products successfully`() = runTest {
 *     // Arrange — стаб репозитория возвращает успех
 *     coEvery { repository.loadProducts() } returns Result.success(fakeProducts)
 *
 *     // Act — вызываем загрузку
 *     viewModel.loadProducts()
 *     advanceUntilIdle()
 *
 *     // Assert — проверяем состояние через Turbine
 *     viewModel.uiState.test {
 *         val initialState = awaitItem()
 *         assertTrue(initialState.isLoading)
 *
 *         val loadedState = awaitItem()
 *         assertFalse(loadedState.isLoading)
 *         assertEquals(3, loadedState.products.size)
 *     }
 * }
 */
class ProductsViewModel(
    private val repository: ProductsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductsUiState(isLoading = true))
    val uiState: StateFlow<ProductsUiState> = _uiState.asStateFlow()

    private var allProducts: List<ProductUiModel> = emptyList()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            repository.loadProducts()
                .onSuccess { products ->
                    allProducts = products
                    val filtered = filterBySearch(products, _uiState.value.searchQuery)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            products = filtered,
                            error = null
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message ?: "Неизвестная ошибка"
                        )
                    }
                }
        }
    }

    fun onSearchChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        val filtered = filterBySearch(allProducts, query)
        _uiState.update { it.copy(products = filtered) }
    }

    fun addToFavorites(productId: Int) {
        _uiState.update { state ->
            val newFavorites = state.favorites.toMutableSet()
            if (newFavorites.contains(productId)) {
                newFavorites.remove(productId)
            } else {
                newFavorites.add(productId)
            }
            state.copy(favorites = newFavorites)
        }
    }

    private fun filterBySearch(
        products: List<ProductUiModel>,
        query: String
    ): List<ProductUiModel> {
        if (query.isBlank()) return products
        return products.filter { it.name.contains(query, ignoreCase = true) }
    }
}
