package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * JCP-05: Unit-тесты маппера продуктов
 *
 * Задание: напиши unit-тесты для ProductMapper.
 *
 * Протестируй все методы маппера:
 * 1. Маппинг одного DTO со всеми заполненными полями
 * 2. Маппинг DTO с null-ценой
 * 3. Маппинг DTO с null-описанием
 * 4. Маппинг списка DTO
 * 5. Маппинг пустого списка
 *
 * ПРИМЕР из урока RCA-47: AAA-структура теста
 *
 * @Test
 * fun `maps DTO to UI model correctly`() {
 *     // Arrange
 *     val dto = ProductDto(id = 1, name = "Молоко", price = 89.90, description = "Свежее")
 *
 *     // Act
 *     val result = ProductMapper.map(dto)
 *
 *     // Assert
 *     assertEquals(1, result.id)
 *     assertEquals("Молоко", result.name)
 *     assertEquals("89.9", result.price)
 *     assertEquals("Свежее", result.description)
 * }
 */
class ProductMapperTest {

    // TODO 1: Протестируй маппинг одного DTO со всеми полями
    // Убедись, что id, name, price, description маппятся правильно
    @Test
    fun `maps DTO to UI model correctly`() {
        // Arrange: создай ProductDto с заполненными полями

        // Act: вызови ProductMapper.map(dto)

        // Assert: проверь каждое поле результата через assertEquals
    }

    // TODO 2: Протестируй обработку null-цены
    // Ожидается: price = "Цена не указана"
    @Test
    fun `handles null price`() {
        // Arrange: создай ProductDto с price = null

        // Act: вызови ProductMapper.map(dto)

        // Assert: проверь, что цена = "Цена не указана"
    }

    // TODO 3: Протестируй обработку null-описания
    // Ожидается: description = ""
    @Test
    fun `handles null description`() {
        // Arrange: создай ProductDto с description = null

        // Act: вызови ProductMapper.map(dto)

        // Assert: проверь, что описание = ""
    }

    // TODO 4: Протестируй маппинг списка из 3 DTO
    // Убедись, что размер результата = 3, и элементы маппятся верно
    @Test
    fun `maps list of DTOs correctly`() {
        // Arrange: создай список из 3 ProductDto (например, Молоко, Хлеб, Сыр)

        // Act: вызови ProductMapper.mapList(dtos)

        // Assert: assertEquals(3, result.size), проверь имя первого элемента
    }

    // TODO 5: Протестируй маппинг пустого списка
    // Ожидается: пустой список
    @Test
    fun `handles empty list`() {
        // Arrange: пустой список

        // Act: вызови ProductMapper.mapList(emptyList())

        // Assert: проверь, что результат пуст (assertEquals + isEmpty)
    }
}
