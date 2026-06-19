package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

/**
 * JCP-05: Compose UI-тесты экрана продуктов
 *
 * Задание: напиши UI-тесты для ProductsScreen.
 *
 * Протестируй отображение экрана в разных состояниях:
 * 1. Отображение списка из нескольких продуктов (названия видны)
 * 2. Отображение индикатора загрузки при isLoading = true
 * 3. Отображение пустого состояния при products = emptyList()
 * 4. Поиск фильтрует отображаемые продукты
 * 5. Клик по продукту вызывает колбэк onProductClick
 *
 * ПРИМЕР из урока RCA-50: Compose UI тест
 *
 * @Test
 * fun displaysProductList() {
 *     composeRule.setContent {
 *         ProductsScreen(
 *             uiState = ProductsUiState(
 *                 products = listOf(
 *                     ProductUiModel(1, "Молоко", "89.9", ""),
 *                     ProductUiModel(2, "Хлеб", "49.9", "")
 *                 )
 *             )
 *         )
 *     }
 *
 *     composeRule
 *         .onNodeWithTag("product_list")
 *         .assertExists()
 *
 *     composeRule
 *         .onNodeWithText("Молоко")
 *         .assertIsDisplayed()
 *
 *     composeRule
 *         .onNodeWithText("Хлеб")
 *         .assertIsDisplayed()
 * }
 */
class ProductsScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    // TODO 1: Протестируй отображение списка продуктов
    // Подставь uiState с несколькими продуктами и проверь, что их названия отображаются
    @Test
    fun `displays product list`() {
        // Arrange: создай список продуктов и uiState
        // val products = listOf(
        //     ProductUiModel(1, "Молоко", "89.9", "Свежее"),
        //     ProductUiModel(2, "Хлеб", "49.9", "Бородинский"),
        //     ProductUiModel(3, "Сыр", "250.0", "Гауда")
        // )

        // Act: установи контент
        // composeRule.setContent {
        //     ProductsScreen(uiState = ProductsUiState(products = products))
        // }

        // Assert: проверь наличие списка и названий продуктов
        // composeRule.onNodeWithTag("product_list").assertExists()
        // composeRule.onNodeWithText("Молоко").assertIsDisplayed()
        // composeRule.onNodeWithText("Хлеб").assertIsDisplayed()
        // composeRule.onNodeWithText("Сыр").assertIsDisplayed()
    }

    // TODO 2: Протестируй отображение индикатора загрузки
    // При isLoading = true должен показываться loading_indicator
    @Test
    fun `shows loading indicator when isLoading is true`() {
        // Arrange: uiState с isLoading = true

        // Act: composeRule.setContent { ProductsScreen(uiState = ...) }

        // Assert: composeRule.onNodeWithTag("loading_indicator").assertIsDisplayed()
    }

    // TODO 3: Протестируй отображение пустого состояния
    // При products = emptyList() и isLoading = false должен показываться empty_state
    @Test
    fun `shows empty state when product list is empty`() {
        // Arrange: uiState с пустым списком и isLoading = false

        // Act: composeRule.setContent { ProductsScreen(uiState = ...) }

        // Assert: composeRule.onNodeWithTag("empty_state").assertIsDisplayed()
    }

    // TODO 4: Протестируй фильтрацию продуктов через поле поиска
    // Введи текст в search_field и проверь, что отфильтрованные продукты отображаются/скрываются
    @Test
    fun `search filters displayed products`() {
        // Arrange: список продуктов и onSearchChanged-колбэк
        // var searchQuery = ""
        // val products = listOf(
        //     ProductUiModel(1, "Молоко", "89.9", ""),
        //     ProductUiModel(2, "Хлеб", "49.9", "")
        // )

        // Act: composeRule.setContent {
        //     ProductsScreen(
        //         uiState = ProductsUiState(products = products, searchQuery = searchQuery),
        //         onSearchChanged = { searchQuery = it }
        //     )
        // }
        // // Введи текст в поле поиска
        // composeRule.onNodeWithTag("search_field").performTextInput("Мол")

        // Assert: "Молоко" отображается, "Хлеб" — нет
        // composeRule.onNodeWithText("Молоко").assertIsDisplayed()
        // composeRule.onNodeWithText("Хлеб").assertDoesNotExist()
    }

    // TODO 5: Протестируй клик по продукту
    // При клике на продукт должен вызываться onProductClick с правильным id
    @Test
    fun `clicking product calls onProductClick`() {
        // Arrange: список продуктов и счётчик кликов
        // var clickedId = -1

        // Act: composeRule.setContent {
        //     ProductsScreen(
        //         uiState = ProductsUiState(products = listOf(ProductUiModel(1, "Молоко", "89.9", ""))),
        //         onProductClick = { clickedId = it }
        //     )
        // }
        // composeRule.onNodeWithTag("product_item_1").performClick()

        // Assert: проверь, что clickedId == 1
    }
}
