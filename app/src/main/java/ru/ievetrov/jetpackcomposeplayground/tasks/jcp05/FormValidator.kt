package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

/**
 * JCP-05: Практика написания тестов — Валидатор формы
 *
 * Задание: написать unit-тесты для готового валидатора
 * в src/test/java/.../tasks/jcp05/FormValidatorTest.kt.
 *
 * Класс FormValidator уже реализован и содержит два метода:
 * 1. validateEmail(email) — проверяет, что email содержит @ и домен (≥ 4 символа после @)
 * 2. validateName(name) — проверяет, что имя содержит ≥ 3 символов
 *
 * Оба метода возвращают ValidationResult:
 * - ValidationResult(true) — валидация пройдена
 * - ValidationResult(false, "текст ошибки") — ошибка с сообщением
 *
 * Что нужно протестировать:
 * 1. Валидный email (например "user@domain.com") → isValid = true
 * 2. Email без домена ("user@") → isValid = false
 * 3. Email без @ ("userdomain.com") → isValid = false
 * 4. Пустой email ("") → isValid = false
 * 5. Валидное имя ("Анна") → isValid = true
 * 6. Короткое имя ("Ан") → isValid = false
 *
 * ПРИМЕР из урока RCA-48: Параметризованные тесты
 *
 * @ParameterizedTest
 * @MethodSource("invalidEmails")
 * fun `rejects invalid email`(email: String, expectedError: String) {
 *     val result = FormValidator.validateEmail(email)
 *
 *     assertFalse(result.isValid)
 *     assertEquals(expectedError, result.errorMessage)
 * }
 *
 * companion object {
 *     @JvmStatic
 *     fun invalidEmails() = listOf(
 *         Arguments.of("user@", "Некорректный email"),
 *         Arguments.of("userdomain.com", "Некорректный email"),
 *         Arguments.of("", "Email не может быть пустым")
 *     )
 * }
 */
object FormValidator {

    /**
     * Валидирует email.
     * Требования: не пустой, содержит '@', после '@' минимум 4 символа (домен).
     */
    fun validateEmail(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(false, "Email не может быть пустым")
        }
        val atIndex = email.indexOf('@')
        if (atIndex == -1) {
            return ValidationResult(false, "Некорректный email")
        }
        val domain = email.substring(atIndex + 1)
        if (domain.length < 4) {
            return ValidationResult(false, "Некорректный email")
        }
        return ValidationResult(true)
    }

    /**
     * Валидирует имя.
     * Требования: минимум 3 символа.
     */
    fun validateName(name: String): ValidationResult {
        if (name.length < 3) {
            return ValidationResult(false, "Имя должно содержать минимум 3 символа")
        }
        return ValidationResult(true)
    }
}
