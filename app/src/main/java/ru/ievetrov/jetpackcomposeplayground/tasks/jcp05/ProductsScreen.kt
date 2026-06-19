package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

/**
 * JCP-05: Практика написания тестов — Экран продуктов
 *
 * Задание: написать Compose UI-тесты для ProductsScreen
 * в src/androidTest/java/.../tasks/jcp05/ProductsScreenTest.kt.
 *
 * Экран полностью stateless — принимает состояние и колбэки извне.
 * Это позволяет тестировать его изолированно, подставляя любые данные.
 *
 * Ключевые testTag, которые нужно использовать в тестах:
 * - "products_screen" — корневой контейнер экрана
 * - "search_field" — поле поиска
 * - "product_list" — список продуктов (LazyColumn)
 * - "loading_indicator" — индикатор загрузки (показывается при isLoading = true)
 * - "empty_state" — текст «Список продуктов пуст»
 * - "error_state" — текст ошибки
 * - "product_item_{id}" — карточка конкретного продукта
 * - "favorite_button_{id}" — кнопка избранного для продукта
 *
 * Что нужно протестировать:
 * 1. Отображение списка из нескольких продуктов (названия видны)
 * 2. Отображение индикатора загрузки при isLoading = true
 * 3. Отображение пустого состояния при products = emptyList()
 * 4. Поиск фильтрует отображаемые продукты
 * 5. Клик по продукту вызывает колбэк onProductClick
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    uiState: ProductsUiState,
    onProductClick: (Int) -> Unit = {},
    onSearchChanged: (String) -> Unit = {},
    onAddToFavorites: (Int) -> Unit = {}
) {
    JetpackComposePlaygroundTheme {
        Surface(
            modifier = Modifier.padding(16.dp), color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("products_screen")
            ) {
                Text(
                    text = "JCP-05: Тестирование экрана продуктов",
                    style = MaterialTheme.typography.headlineMedium
                )

                /**
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

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = uiState.searchQuery,
                    onValueChange = onSearchChanged,
                    label = { Text("Поиск продуктов") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("search_field"),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                when {
                    uiState.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(48.dp)
                                    .testTag("loading_indicator")
                            )
                        }
                    }

                    uiState.error != null -> {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = uiState.error,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.testTag("error_state")
                            )
                        }
                    }

                    uiState.products.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Список продуктов пуст",
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.testTag("empty_state")
                            )
                        }
                    }

                    else -> {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .fillMaxSize()
                                .testTag("product_list")
                        ) {
                            items(uiState.products, key = { it.id }) { product ->
                                ProductCard(
                                    product = product,
                                    isFavorite = uiState.favorites.contains(product.id),
                                    onCardClick = { onProductClick(product.id) },
                                    onFavoriteClick = { onAddToFavorites(product.id) })
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductCard(
        product: ProductUiModel,
        isFavorite: Boolean,
        onCardClick: () -> Unit,
        onFavoriteClick: () -> Unit
    ) {
        Card(
            onClick = onCardClick,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("product_item_${product.id}")
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier.testTag("favorite_button_${product.id}")
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = if (isFavorite) "Удалить из избранного" else "Добавить в избранное",
                        tint = if (isFavorite) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = product.name, style = MaterialTheme.typography.titleMedium
                    )
                    if (product.description.isNotBlank()) {
                        Text(
                            text = product.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Text(
                    text = "${product.price} ₽",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    JetpackComposePlaygroundTheme {
        ProductsScreen(
            uiState = ProductsUiState(
                products = listOf(
                    ProductUiModel(1, "Молоко", "89.9", "Свежее"),
                    ProductUiModel(2, "Хлеб", "49.9", "Бородинский"),
                    ProductUiModel(3, "Сыр", "250.0", "Гауда")
                )
            )
        )
    }
}

