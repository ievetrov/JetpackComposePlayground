package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * JCP-05: Практика написания тестов — Room-entity продукта
 *
 * Используется в интеграционном тесте для проверки кеширования данных в Room.
 * ProductsRepositoryImpl сохраняет продукты в базу данных через ProductDao.
 *
 * Ключевая цель: в тесте проверить, что после loadProducts()
 * данные появляются в DAO (dao.getAllProducts().isNotEmpty()).
 */
@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Double?,
    val description: String?
)

/**
 * Преобразует [ProductEntity] из базы данных в [ProductUiModel] для UI.
 */
fun ProductEntity.toUiModel(): ProductUiModel = ProductUiModel(
    id = id,
    name = name,
    price = price?.toString() ?: "Цена не указана",
    description = description ?: ""
)
