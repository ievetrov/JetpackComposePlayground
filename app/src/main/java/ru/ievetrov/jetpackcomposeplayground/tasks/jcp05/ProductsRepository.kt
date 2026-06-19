package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

/**
 * Репозиторий продуктов.
 *
 * Используется в [ProductsViewModel] для загрузки данных.
 * Для тестов можно подставить реализацию с помощью MockK.
 */
interface ProductsRepository {
    suspend fun loadProducts(): Result<List<ProductUiModel>>
    suspend fun clearCache()
}
