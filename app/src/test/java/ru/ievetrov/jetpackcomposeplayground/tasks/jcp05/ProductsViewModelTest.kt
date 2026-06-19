package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * JCP-05: Unit-тесты ViewModel продуктов
 *
 * Задание: напиши unit-тесты для ProductsViewModel.
 *
 * Протестируй поведение ViewModel:
 * 1. Начальное состояние — isLoading = true
 * 2. Успешная загрузка продуктов
 * 3. Ошибка загрузки — error не null
 * 4. Фильтрация по поисковому запросу
 * 5. Добавление в избранное
 *
 * Используй MockK для стаба ProductsRepository и Turbine для проверки StateFlow.
 *
 * ПРИМЕР из урока RCA-49: Тест ViewModel с Turbine
 *
 * @Test
 * fun `loads products successfully`() = runTest {
 *     // Arrange
 *     coEvery { repository.loadProducts() } returns Result.success(fakeProducts)
 *
 *     // Act — ViewModel запускает загрузку в init, ждём корутины
 *     advanceUntilIdle()
 *
 *     // Assert — проверяем через Turbine
 *     viewModel.uiState.test {
 *         val state = awaitItem()
 *         assertFalse(state.isLoading)
 *         assertEquals(3, state.products.size)
 *     }
 * }
 */
@OptIn(ExperimentalCoroutinesApi::class)
class ProductsViewModelTest {

    private val repository: ProductsRepository = mockk(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: ProductsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // TODO 1: Протестируй начальное состояние ViewModel
    // ViewModel в init вызывает loadProducts(), поэтому нужно настроить стаб ДО создания
    @Test
    fun `initial state shows loading`() = runTest {
        // Arrange: настрой coEvery { repository.loadProducts() } чтобы вернуть Result.success(emptyList())

        // Act: создай ViewModel И вызови advanceUntilIdle()

        // Assert: через viewModel.uiState.test { } проверь что isLoading = true (это первое emitted состояние)
    }

    // TODO 2: Протестируй успешную загрузку продуктов
    // Проверь, что после загрузки isLoading = false и список не пуст
    @Test
    fun `loads products successfully`() = runTest {
        // Arrange: создай список фейковых продуктов и настрой стаб репозитория
        // val fakeProducts = listOf(ProductUiModel(1, "Молоко", "89.9", ""), ...)
        // coEvery { repository.loadProducts() } returns Result.success(fakeProducts)

        // Act: создай ViewModel и дождись корутин
        // val viewModel = ProductsViewModel(repository)
        // advanceUntilIdle()

        // Assert: через Turbine проверь второе emitted состояние (после загрузки)
        // viewModel.uiState.test {
        //     val initialState = awaitItem()  // isLoading = true
        //     val loadedState = awaitItem()   // isLoading = false, products не пуст
        //     assertFalse(loadedState.isLoading)
        //     assertEquals(3, loadedState.products.size)
        // }
    }

    // TODO 3: Протестируй ошибку загрузки
    // Проверь, что при ошибке: isLoading = false, error не null
    @Test
    fun `handles load error`() = runTest {
        // Arrange: coEvery { repository.loadProducts() } returns Result.failure(RuntimeException("Сетевая ошибка"))

        // Act: создай ViewModel, advanceUntilIdle()

        // Assert: проверь что error != null и содержит сообщение об ошибке
    }

    // TODO 4: Протестируй фильтрацию по поисковому запросу
    // После загрузки вызови onSearchChanged("Мол") — в списке должен остаться только "Молоко"
    @Test
    fun `search filters products`() = runTest {
        // Arrange: стаб репозитория возвращает несколько продуктов (Молоко, Хлеб, Сыр)
        // Создай ViewModel и дождись загрузки

        // Act: вызови viewModel.onSearchChanged("Мол")

        // Assert: через Turbine проверь что products.size == 1 и содержит "Молоко"
    }

    // TODO 5: Протестируй добавление в избранное
    // Проверь с помощью awaitItem() что state обновился немедленно
    @Test
    fun `adding to favorites updates state`() = runTest {
        // Arrange: настрой стаб, создай ViewModel, загрузи продукты

        // Act: вызови viewModel.addToFavorites(productId = 1)

        // Assert: через Turbine получи новый state и проверь favorites
        // viewModel.uiState.test {
        //     val state = awaitItem()
        //     assertTrue(state.favorites.contains(1))
        // }
    }
}
