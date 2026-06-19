package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.ievetrov.jetpackcomposeplayground.MainActivity

/**
 * JCP-05: Практика написания тестов — Kaspresso-тест (задание 6, дополнительное)
 *
 * Задание: написать end-to-end тест экрана продуктов с помощью Kaspresso.
 *
 * В отличие от ComposeTestRule, здесь запускается настоящая активность
 * с реальной навигацией. Каждый шаг теста обёрнут в step(), что делает
 * отчёт о падении наглядным: скриншот + имя шага, где произошла ошибка.
 *
 * Ориентируйтесь на урок RCA-52.
 *
 * Что нужно сделать:
 * 1. Запустить MainActivity с помощью activityScenarioRule
 * 2. В первом тесте: перейти к экрану продуктов, проверить что он открылся
 * 3. Во втором тесте: ввести текст в поле поиска, проверить фильтрацию
 * 4. Каждое логическое действие оборачивайте в step("описание") { }
 *
 * ПРИМЕР из урока RCA-52: структура Kaspresso-теста
 *
 * class SampleKaspressoTest : TestCase() {
 *
 *     @get:Rule
 *     val activityRule = activityScenarioRule<MainActivity>()
 *
 *     @Test
 *     fun shouldShowMainScreen() = run {
 *         step("Проверить заголовок главного экрана") {
 *             onView(withText("JetpackComposePlayground"))
 *                 .check(matches(isDisplayed()))
 *         }
 *         step("Нажать на карточку JCP-05") {
 *             onView(withText("Тестирование экрана продуктов"))
 *                 .perform(click())
 *         }
 *     }
 * }
 */
@RunWith(AndroidJUnit4::class)
class KaspressoProductsTest : TestCase() {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * Тест 1: Экран продуктов открывается при запуске
     *
     * Шаги:
     * 1. Запускается MainActivity (автоматически через activityRule)
     * 2. Находим карточку "Тестирование экрана продуктов" на главном экране
     * 3. Нажимаем на неё
     * 4. Проверяем что открылся экран с тегом "products_screen"
     */
    @Test
    fun shouldDisplayProductsScreenOnLaunch() = run {
        // TODO 1: Перейти к экрану продуктов
        // Подсказка: используйте onView(withText("Тестирование экрана продуктов")).perform(click())
        // внутри step("Открыть экран продуктов") { }
        step("Открыть экран продуктов") {
            // TODO: нажмите на карточку "Тестирование экрана продуктов" в MainScreen
        }

        // TODO 2: Проверить что экран отображается
        // Подсказка: используйте onView(withTagValue(equalTo("products_screen")))
        // или onView(withId(...)).check(matches(isDisplayed()))
        step("Проверить что экран продуктов открылся") {
            // TODO: проверьте что элемент с тегом "products_screen" виден
        }
    }

    /**
     * Тест 2: Поиск фильтрует список продуктов
     *
     * Шаги:
     * 1. Открываем экран продуктов (как в тесте 1)
     * 2. Находим поле поиска по тегу "search_field"
     * 3. Вводим текст поиска
     * 4. Проверяем что совпадающий продукт виден, а несовпадающий — нет
     */
    @Test
    fun shouldFilterProductsBySearch() = run {
        step("Открыть экран продуктов") {
            // TODO: нажмите на карточку "Тестирование экрана продуктов" в MainScreen
        }

        // TODO 3: Ввести текст в поле поиска
        // Подсказка: onView(withTagValue(equalTo("search_field")))
        //     .perform(typeText("Молоко"), closeSoftKeyboard())
        step("Ввести запрос в поиск") {
            // TODO: найдите поле с тегом "search_field" и введите текст
        }

        // TODO 4: Проверить результаты фильтрации
        // Подсказка: onView(withText("Молоко")).check(matches(isDisplayed()))
        //            onView(withText("Хлеб")).check(doesNotExist())
        step("Проверить отфильтрованные продукты") {
            // TODO: проверьте что нужный продукт виден, а остальные скрыты
        }
    }
}
