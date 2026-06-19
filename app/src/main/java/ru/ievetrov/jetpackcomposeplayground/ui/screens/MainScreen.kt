package ru.ievetrov.jetpackcomposeplayground.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

@Composable
fun MainScreen(
    // JCP-01
    navigateToDeclarativeComponents: () -> Unit = {},
    navigateToModifiersExample: () -> Unit = {},
    navigateToBasicElements: () -> Unit = {},
    navigateToSimpleList: () -> Unit = {},
    navigateToSimpleState: () -> Unit = {},
    
    // JCP-02
    navigateToSimpleButtons: () -> Unit = {},
    navigateToSelectableList: () -> Unit = {},
    navigateToExpandableCards: () -> Unit = {},
    navigateToFilterableList: () -> Unit = {},
    navigateToValidationForm: () -> Unit = {},
    navigateToParentChildComponent: () -> Unit = {},
    navigateToTimer: () -> Unit = {},
    
    // JCP-03
    navigateToSimpleNavigation: () -> Unit = {},
    navigateToNavigationWithParams: () -> Unit = {},
    navigateToNavigationWithObjects: () -> Unit = {},
    navigateToNestedNavigation: () -> Unit = {},
    navigateToDeepLinks: () -> Unit = {},
    navigateToNavigationAnimations: () -> Unit = {},
    navigateToBackStackManagement: () -> Unit = {},
    
    // JCP-04
    navigateToBasicCoroutine: () -> Unit = {},
    navigateToParallelCoroutines: () -> Unit = {},
    navigateToCoroutineLifecycle: () -> Unit = {},
    navigateToBasicFlow: () -> Unit = {},
    navigateToFlowErrorHandling: () -> Unit = {},
    navigateToStateFlow: () -> Unit = {},
    navigateToSharedFlow: () -> Unit = {},
    
    // JCP-05
    navigateToProductsScreen: () -> Unit = {}
) {
    JetpackComposePlaygroundTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Jetpack Compose Playground",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            
            // JCP-01: Введение в декларативную верстку
            Text(
                text = "JCP-01: Введение в декларативную верстку",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
            
            TaskCard(
                title = "Декларативные компоненты",
                description = "Создание композабл-функций и демонстрация декларативного подхода",
                onClick = navigateToDeclarativeComponents
            )
            
            TaskCard(
                title = "Работа с модификаторами",
                description = "Изучение основных модификаторов Compose и порядка их применения",
                onClick = navigateToModifiersExample
            )
            
            TaskCard(
                title = "Базовые UI-элементы",
                description = "Работа с текстом, кнопками, полями ввода и другими базовыми компонентами",
                onClick = navigateToBasicElements
            )
            
            TaskCard(
                title = "Простой список",
                description = "Создание и настройка списка с помощью Column и Scroll",
                onClick = navigateToSimpleList
            )
            
            TaskCard(
                title = "Состояние в Compose",
                description = "Управление состоянием компонентов с помощью remember и mutableStateOf",
                onClick = navigateToSimpleState
            )
            
            
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            
            // JCP-02: Практика по работе с состоянием
            Text(
                text = "JCP-02: Практика по работе с состоянием",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
            
            TaskCard(
                title = "Простое состояние кнопок",
                description = "Реализация счетчика с кнопками увеличения и уменьшения значения",
                onClick = navigateToSimpleButtons
            )
            
            TaskCard(
                title = "Список с выбором",
                description = "Реализация списка элементов с чекбоксами и подсчетом выбранных элементов",
                onClick = navigateToSelectableList
            )
            
            TaskCard(
                title = "Переключаемые карточки",
                description = "Создание списка карточек с возможностью разворачивания/сворачивания",
                onClick = navigateToExpandableCards
            )
            
            TaskCard(
                title = "Фильтрация списка",
                description = "Реализация списка с фильтрацией по категориям с помощью чипсов",
                onClick = navigateToFilterableList
            )
            
            TaskCard(
                title = "Форма с валидацией",
                description = "Создание формы ввода с валидацией полей и отображением ошибок",
                onClick = navigateToValidationForm
            )
            
            TaskCard(
                title = "Подъём состояния",
                description = "Реализация подъема состояния (State Hoisting) между родительским и дочерним компонентами",
                onClick = navigateToParentChildComponent
            )
            
            TaskCard(
                title = "Таймер с состоянием",
                description = "Создание секундомера с использованием LaunchedEffect для обновления времени",
                onClick = navigateToTimer
            )
            
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            
            // JCP-03: Практика разных подходов в Jetpack Navigation
            Text(
                text = "JCP-03: Практика навигации в Jetpack Compose",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
            
            TaskCard(
                title = "Базовая навигация",
                description = "Реализация переходов между экранами с помощью NavController и NavHost",
                onClick = navigateToSimpleNavigation
            )
            
            TaskCard(
                title = "Навигация с параметрами",
                description = "Передача параметров через URL и их получение на целевом экране",
                onClick = navigateToNavigationWithParams
            )
            
            TaskCard(
                title = "Передача объектов",
                description = "Реализация передачи Parcelable-объектов между экранами",
                onClick = navigateToNavigationWithObjects
            )
            
            TaskCard(
                title = "Вложенная навигация",
                description = "Создание сложной навигации с Bottom Navigation и вложенными графами",
                onClick = navigateToNestedNavigation
            )
            
            TaskCard(
                title = "Deep Links",
                description = "Настройка и обработка deepLinks для навигации из внешних источников",
                onClick = navigateToDeepLinks
            )
            
            TaskCard(
                title = "Анимации переходов",
                description = "Реализация различных анимаций при навигации между экранами",
                onClick = navigateToNavigationAnimations
            )
            
            TaskCard(
                title = "Управление бэкстеком",
                description = "Демонстрация различных стратегий управления историей навигации",
                onClick = navigateToBackStackManagement
            )
            
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            
            // JCP-04: Практика работы с корутинами и Flow API
            Text(
                text = "JCP-04: Практика работы с корутинами и Flow API",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
            
            TaskCard(
                title = "Базовая работа с корутинами",
                description = "Запуск корутин с LaunchedEffect и rememberCoroutineScope, обработка ошибок",
                onClick = navigateToBasicCoroutine
            )
            
            TaskCard(
                title = "Параллельные операции",
                description = "Использование async/await для параллельного выполнения задач",
                onClick = navigateToParallelCoroutines
            )
            
            TaskCard(
                title = "Жизненный цикл корутин",
                description = "Управление отменой корутин с DisposableEffect и проверка isActive",
                onClick = navigateToCoroutineLifecycle
            )
            
            TaskCard(
                title = "Создание и сбор Flow",
                description = "Работа с Flow: создание, сбор значений и операторы трансформации",
                onClick = navigateToBasicFlow
            )
            
            TaskCard(
                title = "Обработка ошибок в Flow",
                description = "Использование catch, retry и onCompletion для обработки ошибок",
                onClick = navigateToFlowErrorHandling
            )
            
            TaskCard(
                title = "Работа с StateFlow",
                description = "Использование MutableStateFlow для управления состоянием формы",
                onClick = navigateToStateFlow
            )
            
            TaskCard(
                title = "События с SharedFlow",
                description = "Эмиссия и сбор событий приложения с помощью MutableSharedFlow",
                onClick = navigateToSharedFlow
            )
            
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            
            // JCP-05: Практика написания тестов
            Text(
                text = "JCP-05: Практика написания тестов",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
            
            TaskCard(
                title = "Тестирование экрана продуктов",
                description = "Написание unit-тестов для маппера и валидатора, тестов ViewModel с Turbine, Compose UI-тестов и интеграционного теста репозитория",
                onClick = navigateToProductsScreen
            )
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCard(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
} 