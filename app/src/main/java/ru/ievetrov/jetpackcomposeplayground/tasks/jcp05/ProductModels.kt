package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

/**
 * JCP-05: Практика написания тестов — Модели данных
 *
 * Этот блок посвящён тестированию, а не созданию UI-экранов.
 * Все классы в пакете tasks/jcp05/ уже реализованы — твоя задача
 * написать для них тесты в src/test/ и src/androidTest/.
 *
 * В этом файле собраны data-классы, используемые остальными компонентами:
 * - ProductDto — данные, приходящие из сети (поле price и description nullable)
 * - ProductUiModel — модель для отображения на UI (null → заглушки)
 * - ProductsUiState — полное состояние экрана со списком продуктов
 * - ValidationResult — результат валидации поля формы
 */

/**
 * DTO-модель продукта, имитирующая ответ от сервера.
 * Поля price и description могут быть null — маппер должен это обработать.
 */
data class ProductDto(
    val id: Int,
    val name: String,
    val price: Double?,
    val description: String?
)

/**
 * UI-модель продукта для отображения на экране.
 * Все поля ненулевые — null-значения заменены маппером на заглушки.
 */
data class ProductUiModel(
    val id: Int,
    val name: String,
    val price: String,
    val description: String
)

/**
 * Состояние экрана со списком продуктов.
 */
data class ProductsUiState(
    val isLoading: Boolean = false,
    val products: List<ProductUiModel> = emptyList(),
    val error: String? = null,
    val searchQuery: String = "",
    val favorites: Set<Int> = emptySet()
)

/**
 * Результат валидации одного поля формы.
 */
data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
)
