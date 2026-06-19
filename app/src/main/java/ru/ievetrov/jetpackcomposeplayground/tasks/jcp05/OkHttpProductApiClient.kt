package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

/**
 * JCP-05: Практика написания тестов — HTTP-клиент для загрузки продуктов
 *
 * Реализация ProductApiClient через OkHttp + org.json.
 * В интеграционном тесте baseUrl берётся из MockWebServer:
 *
 * val apiClient = OkHttpProductApiClient(mockWebServer.url("/").toString())
 *
 * MockWebServer перехватывает реальные HTTP-запросы, не требуя сети.
 * JSON парсится через встроенный в Android org.json без дополнительных зависимостей.
 */
class OkHttpProductApiClient(
    private val baseUrl: String,
    private val client: OkHttpClient = OkHttpClient()
) : ProductApiClient {

    override suspend fun fetchProducts(): List<ProductDto> = withContext(Dispatchers.IO) {
        val url = if (baseUrl.endsWith("/")) "${baseUrl}products" else "$baseUrl/products"
        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            throw Exception("HTTP ${response.code}: ${response.message}")
        }

        val body = response.body?.string() ?: "[]"
        parseJson(body)
    }

    private fun parseJson(json: String): List<ProductDto> {
        val array = JSONArray(json)
        return (0 until array.length()).map { i ->
            val obj = array.getJSONObject(i)
            ProductDto(
                id = obj.getInt("id"),
                name = obj.getString("name"),
                price = if (obj.isNull("price")) null else obj.getDouble("price"),
                description = if (obj.isNull("description")) null else obj.getString("description")
            )
        }
    }
}
