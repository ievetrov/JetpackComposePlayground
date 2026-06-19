package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

/**
 * JCP-05: Практика написания тестов — Реализация репозитория
 *
 * Задание: написать интеграционный тест для ProductsRepositoryImpl
 * в src/androidTest/java/.../tasks/jcp05/ProductsIntegrationTest.kt.
 *
 * Репозиторий уже реализован и делает следующее:
 * 1. При вызове loadProducts() пытается загрузить данные из сети (через apiClient)
 * 2. При успехе — сохраняет результат в in-memory кеш и возвращает данные
 * 3. При ошибке сети — возвращает данные из кеша (если есть), иначе ошибку
 * 4. clearCache() очищает сохранённые данные
 *
 * Для интеграционного теста используется MockWebServer (OkHttp),
 * который имитирует ответы сервера.
 *
 * Что нужно протестировать:
 * 1. Успешная загрузка и сохранение в кеш
 * 2. Возврат данных из кеша при недоступности сервера
 * 3. Очистка кеша через clearCache()
 *
 * ПРИМЕР из урока RCA-51: Интеграционный тест с MockWebServer + Room
 *
 * @Test
 * fun `fetchesProductsAndSavesToDatabase`() = runTest {
 *     // Arrange — настраиваем MockWebServer
 *     val json = """[{"id":1,"name":"Молоко","price":89.9,"description":"Свежее"}]"""
 *     mockWebServer.enqueue(MockResponse().setBody(json).setResponseCode(200))
 *
 *     // Act — вызываем загрузку
 *     val result = repository.loadProducts()
 *
 *     // Assert — проверяем что данные сохранились в Room
 *     assertTrue(result.isSuccess)
 *     assertTrue(dao.getAllProducts().isNotEmpty())
 * }
 */
class ProductsRepositoryImpl(
    private val apiClient: ProductApiClient,
    private val dao: ProductDao
) : ProductsRepository {

    override suspend fun loadProducts(): Result<List<ProductUiModel>> {
        return try {
            val dtos = apiClient.fetchProducts()
            val entities = dtos.map { dto ->
                ProductEntity(dto.id, dto.name, dto.price, dto.description)
            }
            dao.insertAll(entities)
            Result.success(ProductMapper.mapList(dtos))
        } catch (e: Exception) {
            val cached = dao.getAllProducts()
            if (cached.isNotEmpty()) {
                Result.success(cached.map { it.toUiModel() })
            } else {
                Result.failure(e)
            }
        }
    }

    override suspend fun clearCache() {
        dao.clearAll()
    }
}

/**
 * Интерфейс HTTP-клиента для загрузки продуктов.
 * В реальном приложении здесь был бы Retrofit, но для тестов
 * мы подставляем реализацию, работающую через MockWebServer.
 */
interface ProductApiClient {
    suspend fun fetchProducts(): List<ProductDto>
}
