package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

/**
 * JCP-05: Практика написания тестов — Маппер продуктов
 *
 * Задание: написать unit-тесты для готового маппера
 * в src/test/java/.../tasks/jcp05/ProductMapperTest.kt.
 *
 * Класс ProductMapper уже реализован и делает следующее:
 * 1. Преобразует ProductDto → ProductUiModel
 * 2. Если price == null — подставляет строку "Цена не указана"
 * 3. Если description == null — подставляет пустую строку ""
 * 4. Метод mapList обрабатывает список DTO и возвращает список UI-моделей
 *
 * Что нужно протестировать:
 * 1. Маппинг всех полей валидного DTO (id, name, price, description)
 * 2. Обработку price = null → ожидается "Цена не указана"
 * 3. Обработку description = null → ожидается ""
 * 4. Маппинг списка из нескольких DTO
 * 5. Маппинг пустого списка → ожидается пустой результат
 *
 * ПРИМЕР из урока RCA-47: Структура теста с AAA
 *
 * @Test
 * fun `maps DTO to UI model correctly`() {
 *     // Arrange — подготовка данных
 *     val dto = ProductDto(id = 1, name = "Молоко", price = 89.90, description = "Свежее")
 *
 *     // Act — вызов тестируемого метода
 *     val result = ProductMapper.map(dto)
 *
 *     // Assert — проверка результата
 *     assertEquals(1, result.id)
 *     assertEquals("Молоко", result.name)
 *     assertEquals("89.9", result.price)
 *     assertEquals("Свежее", result.description)
 * }
 */
object ProductMapper {

    /**
     * Преобразует один [ProductDto] в [ProductUiModel].
     * Null-поля заменяются значениями по умолчанию.
     */
    fun map(dto: ProductDto): ProductUiModel {
        return ProductUiModel(
            id = dto.id,
            name = dto.name,
            price = dto.price?.toString() ?: "Цена не указана",
            description = dto.description ?: ""
        )
    }

    /**
     * Преобразует список DTO в список UI-моделей.
     */
    fun mapList(dtos: List<ProductDto>): List<ProductUiModel> {
        return dtos.map { map(it) }
    }
}
