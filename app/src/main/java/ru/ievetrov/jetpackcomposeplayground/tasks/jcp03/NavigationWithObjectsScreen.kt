package ru.ievetrov.jetpackcomposeplayground.tasks.jcp03

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.parcelize.Parcelize
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

@Composable
fun NavigationWithObjectsScreen() {
    JetpackComposePlaygroundTheme {
        val navController = rememberNavController()
        val products = remember {
            mutableStateListOf<ParcelableProduct>().apply {
                addAll(sampleParcelableProducts)
            }
        }

        val currentBackStackEntry by navController.currentBackStackEntryAsState()

        LaunchedEffect(currentBackStackEntry) {
            val currentEntry = navController.currentBackStackEntry ?: return@LaunchedEffect

            currentEntry.savedStateHandle
                .get<ParcelableProduct>("edited_product_state")
                ?.let { updatedProduct ->
                    val index = products.indexOfFirst { it.id == updatedProduct.id }
                    if (index != -1) {
                        products[index] = updatedProduct
                    }
                    currentEntry.savedStateHandle.remove<ParcelableProduct>("edited_product_state")
                }

            currentEntry.savedStateHandle
                .get<Bundle>("edited_product_bundle")
                ?.let { resultBundle ->
                    val updatedProduct =
                        resultBundle.readParcelableCompat<ParcelableProduct>("edited_product_key")

                    if (updatedProduct != null) {
                        val index = products.indexOfFirst { it.id == updatedProduct.id }
                        if (index != -1) {
                            products[index] = updatedProduct
                        }
                    }

                    currentEntry.savedStateHandle.remove<Bundle>("edited_product_bundle")
                }
        }

        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Text(
                    text = "JCP-03: Передача объектов",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                NavHost(
                    navController = navController,
                    startDestination = "products"
                ) {
                    composable("products") {
                        ParcelableProductsList(
                            products = products,
                            onSavedStateHandleClick = { product ->
                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("product_key", product)

                                navController.navigate("product_details_state_handle")
                            },
                            onBundleClick = { product ->
                                val bundle = Bundle().apply {
                                    putParcelable("product_key", product)
                                }

                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("product_bundle", bundle)

                                navController.navigate("product_details_bundle")
                            }
                        )
                    }

                    composable("product_details_state_handle") {
                        val product = navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.get<ParcelableProduct>("product_key")

                        if (product != null) {
                            EditableParcelableProductDetails(
                                title = "Детали товара (savedStateHandle)",
                                product = product,
                                onSaveClick = { updatedProduct ->
                                    navController.previousBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("edited_product_state", updatedProduct)

                                    navController.popBackStack()
                                },
                                onBackClick = { navController.popBackStack() }
                            )
                        } else {
                            ProductNotFound(
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    }

                    composable("product_details_bundle") {
                        val bundle = navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.get<Bundle>("product_bundle")

                        val product =
                            bundle?.readParcelableCompat<ParcelableProduct>("product_key")

                        if (product != null) {
                            EditableParcelableProductDetails(
                                title = "Детали товара (Bundle)",
                                product = product,
                                onSaveClick = { updatedProduct ->
                                    val resultBundle = Bundle().apply {
                                        putParcelable("edited_product_key", updatedProduct)
                                    }

                                    navController.previousBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("edited_product_bundle", resultBundle)

                                    navController.popBackStack()
                                },
                                onBackClick = { navController.popBackStack() }
                            )
                        } else {
                            ProductNotFound(
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Parcelable модель товара
 * Не забудьте включить плагин:
 *
 * plugins {
 *     id("kotlin-parcelize")
 * }
 */
@Parcelize
data class ParcelableProduct(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val availability: Boolean = true,
    val rating: Float = 4.5f
) : Parcelable

val sampleParcelableProducts = listOf(
    ParcelableProduct(1, "Смартфон", "Современный смартфон с большим экраном", 29999.0),
    ParcelableProduct(2, "Ноутбук", "Мощный ноутбук для работы и игр", 59999.0),
    ParcelableProduct(3, "Наушники", "Беспроводные наушники с шумоподавлением", 12999.0),
    ParcelableProduct(4, "Умные часы", "Фитнес-трекер с множеством функций", 15999.0),
    ParcelableProduct(5, "Планшет", "Компактный планшет для развлечений", 24999.0)
)

@Composable
fun ParcelableProductsList(
    products: List<ParcelableProduct>,
    onSavedStateHandleClick: (ParcelableProduct) -> Unit,
    onBundleClick: (ParcelableProduct) -> Unit
) {
    LazyColumn {
        items(products) { product ->
            ParcelableProductItem(
                product = product,
                onSavedStateHandleClick = { onSavedStateHandleClick(product) },
                onBundleClick = { onBundleClick(product) }
            )
        }
    }
}

@Composable
fun ParcelableProductItem(
    product: ParcelableProduct,
    onSavedStateHandleClick: () -> Unit,
    onBundleClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = product.description)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Цена: ${product.price} ₽",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(
                    onClick = onSavedStateHandleClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Через savedStateHandle")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onBundleClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Через Bundle")
                }
            }
        }
    }
}

@Composable
fun EditableParcelableProductDetails(
    title: String,
    product: ParcelableProduct,
    onSaveClick: (ParcelableProduct) -> Unit,
    onBackClick: () -> Unit
) {
    var name by remember(product) { mutableStateOf(product.name) }
    var description by remember(product) { mutableStateOf(product.description) }
    var priceText by remember(product) { mutableStateOf(product.price.toString()) }
    var availability by remember(product) { mutableStateOf(product.availability) }
    var ratingText by remember(product) { mutableStateOf(product.rating.toString()) }

    val parsedPrice = priceText.toDoubleOrNull()
    val parsedRating = ratingText.toFloatOrNull()
    val isValid = parsedPrice != null && parsedRating != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Название") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Описание") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = priceText,
            onValueChange = { priceText = it },
            label = { Text("Цена") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = ratingText,
            onValueChange = { ratingText = it },
            label = { Text("Рейтинг") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("В наличии")
            Spacer(modifier = Modifier.width(12.dp))
            Switch(
                checked = availability,
                onCheckedChange = { availability = it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Полная информация:",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("ID: ${product.id}")
        Text("Название: $name")
        Text("Описание: $description")
        Text("Цена: ${parsedPrice ?: "Некорректно"}")
        Text("Доступность: ${if (availability) "В наличии" else "Нет в наличии"}")
        Text("Рейтинг: ${parsedRating ?: "Некорректно"}")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val updatedProduct = product.copy(
                    name = name,
                    description = description,
                    price = parsedPrice ?: product.price,
                    availability = availability,
                    rating = parsedRating ?: product.rating
                )
                onSaveClick(updatedProduct)
            },
            enabled = isValid,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Сохранить и вернуться")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Назад")
        }
    }
}

@Composable
fun ProductNotFound(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Товар не найден",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBackClick) {
            Text("Назад")
        }
    }
}

inline fun <reified T : Parcelable> Bundle.readParcelableCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelable(key)
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationWithObjectsScreenPreview() {
    NavigationWithObjectsScreen()
}