package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * JCP-05: Unit-тесты валидатора формы
 *
 * Задание: напиши unit-тесты для FormValidator.
 *
 * Протестируй оба метода валидатора:
 * 1. Валидный email → isValid = true
 * 2. Email без домена → isValid = false
 * 3. Email без @ → isValid = false
 * 4. Пустой email → isValid = false
 * 5. Валидное имя → isValid = true
 * 6. Короткое имя → isValid = false
 *
 * ПРИМЕР из урока RCA-48: Тест валидатора
 *
 * @Test
 * fun `valid email passes validation`() {
 *     val result = FormValidator.validateEmail("user@domain.com")
 *
 *     assertTrue(result.isValid)
 *     assertNull(result.errorMessage)
 * }
 */
class FormValidatorTest {

    // TODO 1: Протестируй валидный email
    // Проверь, что result.isValid = true и errorMessage = null
    @Test
    fun `valid email passes validation`() {
        // Arrange: валидный email (например "user@domain.com")

        // Act: вызови FormValidator.validateEmail(email)

        // Assert: assertTrue(result.isValid)
    }

    // TODO 2: Протестируй email без домена
    // Проверь, что result.isValid = false и errorMessage содержит "email"
    @Test
    fun `email without domain fails validation`() {
        // Arrange: email = "user@" (пустой домен)

        // Act: вызови FormValidator.validateEmail(email)

        // Assert: assertFalse(result.isValid)
        //         assertTrue(result.errorMessage?.contains("email") == true)
    }

    // TODO 3: Протестируй email без символа @
    // Проверь, что result.isValid = false
    @Test
    fun `email without at symbol fails validation`() {
        // Arrange: email = "userdomain.com" (без @)

        // Act: вызови FormValidator.validateEmail(email)

        // Assert: assertFalse(result.isValid)
    }

    // TODO 4: Протестируй пустой email
    // Проверь, что result.isValid = false
    @Test
    fun `empty email fails validation`() {
        // Arrange: email = ""

        // Act: вызови FormValidator.validateEmail(email)

        // Assert: assertFalse(result.isValid)
    }

    // TODO 5: Протестируй валидное имя (≥ 3 символов)
    // Проверь, что result.isValid = true
    @Test
    fun `valid name passes validation`() {
        // Arrange: name = "Анна" (4 символа)

        // Act: вызови FormValidator.validateName(name)

        // Assert: assertTrue(result.isValid)
    }

    // TODO 6: Протестируй имя короче 3 символов
    // Проверь, что result.isValid = false и errorMessage содержит "символ"
    @Test
    fun `name too short fails validation`() {
        // Arrange: name = "Ан" (2 символа)

        // Act: вызови FormValidator.validateName(name)

        // Assert: assertFalse(result.isValid)
        //         assertTrue(result.errorMessage?.contains("символ") == true)
    }
}
