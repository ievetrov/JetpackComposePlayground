package ru.ievetrov.jetpackcomposeplayground.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp01.BasicElementsScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp01.DeclarativeComponentsScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp01.ModifiersExampleScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp01.SimpleListScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp01.SimpleStateScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp02.ExpandableCardsScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp02.FilterableListScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp02.ParentScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp02.SelectableListScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp02.SimpleButtonsScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp02.TimerScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp02.ValidationFormScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp03.BackStackManagementScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp03.DeepLinksScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp03.NavigationAnimationsScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp03.NavigationWithObjectsScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp03.NavigationWithParamsScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp03.NestedNavigationScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp03.SimpleNavigationScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp04.BasicCoroutineScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp04.BasicFlowScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp04.CoroutineLifecycleScreen

import ru.ievetrov.jetpackcomposeplayground.tasks.jcp04.FlowErrorHandlingScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp04.ParallelCoroutinesScreen

import ru.ievetrov.jetpackcomposeplayground.tasks.jcp04.SharedFlowScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp04.StateFlowScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp05.ProductUiModel
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp05.ProductsScreen
import ru.ievetrov.jetpackcomposeplayground.tasks.jcp05.ProductsUiState
import ru.ievetrov.jetpackcomposeplayground.ui.screens.MainScreen

/**
 * Основные маршруты навигации в приложении
 */
sealed class JCPDestination(val route: String) {
    object Home : JCPDestination("home")
    
    // JCP-01: Введение в декларативную верстку
    object DeclarativeComponents : JCPDestination("jcp_01/declarative_components")
    object ModifiersExample : JCPDestination("jcp_01/modifiers_example")
    object BasicElements : JCPDestination("jcp_01/basic_elements")
    object SimpleList : JCPDestination("jcp_01/simple_list")
    object SimpleState : JCPDestination("jcp_01/simple_state")
    
    // JCP-02: Практика по работе с состоянием
    object SimpleButtons : JCPDestination("jcp_02/simple_buttons")
    object SelectableList : JCPDestination("jcp_02/selectable_list")
    object ExpandableCards : JCPDestination("jcp_02/expandable_cards")
    object FilterableList : JCPDestination("jcp_02/filterable_list")
    object ValidationForm : JCPDestination("jcp_02/validation_form")
    object ParentChildComponent : JCPDestination("jcp_02/parent_child")
    object Timer : JCPDestination("jcp_02/timer")
    
    // JCP-03: Практика разных подходов в Jetpack Navigation
    object SimpleNavigation : JCPDestination("jcp_03/simple_navigation")
    object NavigationWithParams : JCPDestination("jcp_03/navigation_with_params")
    object NavigationWithObjects : JCPDestination("jcp_03/navigation_with_objects")
    object NestedNavigation : JCPDestination("jcp_03/nested_navigation")
    object DeepLinks : JCPDestination("jcp_03/deep_links")
    object NavigationAnimations : JCPDestination("jcp_03/navigation_animations")
    object BackStackManagement : JCPDestination("jcp_03/back_stack_management")
    
    // JCP-04: Практика работы с корутинами и Flow API
    object BasicCoroutine : JCPDestination("jcp_04/basic_coroutine")
    object ParallelCoroutines : JCPDestination("jcp_04/parallel_coroutines")
    object CoroutineLifecycle : JCPDestination("jcp_04/coroutine_lifecycle")
    object BasicFlow : JCPDestination("jcp_04/basic_flow")
    object FlowErrorHandling : JCPDestination("jcp_04/flow_error_handling")
    object StateFlow : JCPDestination("jcp_04/state_flow")
    object SharedFlow : JCPDestination("jcp_04/shared_flow")
    
    // JCP-05: Практика написания тестов
    object ProductsScreen : JCPDestination("jcp_05/products_screen")

}

@Composable
fun JetpackComposePlaygroundNavHost() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = JCPDestination.Home.route
    ) {
        composable(JCPDestination.Home.route) {
            MainScreen(
                // JCP-01
                navigateToDeclarativeComponents = {
                    navController.navigate(JCPDestination.DeclarativeComponents.route)
                },
                navigateToModifiersExample = {
                    navController.navigate(JCPDestination.ModifiersExample.route)
                },
                navigateToBasicElements = {
                    navController.navigate(JCPDestination.BasicElements.route)
                },
                navigateToSimpleList = {
                    navController.navigate(JCPDestination.SimpleList.route)
                },
                navigateToSimpleState = {
                    navController.navigate(JCPDestination.SimpleState.route)
                },
                
                // JCP-02
                navigateToSimpleButtons = {
                    navController.navigate(JCPDestination.SimpleButtons.route)
                },
                navigateToSelectableList = {
                    navController.navigate(JCPDestination.SelectableList.route)
                },
                navigateToExpandableCards = {
                    navController.navigate(JCPDestination.ExpandableCards.route)
                },
                navigateToFilterableList = {
                    navController.navigate(JCPDestination.FilterableList.route)
                },
                navigateToValidationForm = {
                    navController.navigate(JCPDestination.ValidationForm.route)
                },
                navigateToParentChildComponent = {
                    navController.navigate(JCPDestination.ParentChildComponent.route)
                },
                navigateToTimer = {
                    navController.navigate(JCPDestination.Timer.route)
                },
                
                // JCP-03
                navigateToSimpleNavigation = {
                    navController.navigate(JCPDestination.SimpleNavigation.route)
                },
                navigateToNavigationWithParams = {
                    navController.navigate(JCPDestination.NavigationWithParams.route)
                },
                navigateToNavigationWithObjects = {
                    navController.navigate(JCPDestination.NavigationWithObjects.route)
                },
                navigateToNestedNavigation = {
                    navController.navigate(JCPDestination.NestedNavigation.route)
                },
                navigateToDeepLinks = {
                    navController.navigate(JCPDestination.DeepLinks.route)
                },
                navigateToNavigationAnimations = {
                    navController.navigate(JCPDestination.NavigationAnimations.route)
                },
                navigateToBackStackManagement = {
                    navController.navigate(JCPDestination.BackStackManagement.route)
                },
                
                // JCP-04
                navigateToBasicCoroutine = {
                    navController.navigate(JCPDestination.BasicCoroutine.route)
                },
                navigateToParallelCoroutines = {
                    navController.navigate(JCPDestination.ParallelCoroutines.route)
                },
                navigateToCoroutineLifecycle = {
                    navController.navigate(JCPDestination.CoroutineLifecycle.route)
                },
                navigateToBasicFlow = {
                    navController.navigate(JCPDestination.BasicFlow.route)
                },
                navigateToFlowErrorHandling = {
                    navController.navigate(JCPDestination.FlowErrorHandling.route)
                },
                navigateToStateFlow = {
                    navController.navigate(JCPDestination.StateFlow.route)
                },
                navigateToSharedFlow = {
                    navController.navigate(JCPDestination.SharedFlow.route)
                },
                
                // JCP-05
                navigateToProductsScreen = {
                    navController.navigate(JCPDestination.ProductsScreen.route)
                },

            )
        }
        
        // JCP-01: Маршруты
        composable(JCPDestination.DeclarativeComponents.route) {
            DeclarativeComponentsScreen()
        }
        
        composable(JCPDestination.ModifiersExample.route) {
            ModifiersExampleScreen()
        }
        
        composable(JCPDestination.BasicElements.route) {
            BasicElementsScreen()
        }
        
        composable(JCPDestination.SimpleList.route) {
            SimpleListScreen()
        }
        
        composable(JCPDestination.SimpleState.route) {
            SimpleStateScreen()
        }
        
        
        // JCP-02: Маршруты
        composable(JCPDestination.SimpleButtons.route) {
            SimpleButtonsScreen()
        }
        
        composable(JCPDestination.SelectableList.route) {
            SelectableListScreen()
        }
        
        composable(JCPDestination.ExpandableCards.route) {
            ExpandableCardsScreen()
        }
        
        composable(JCPDestination.FilterableList.route) {
            FilterableListScreen()
        }
        
        composable(JCPDestination.ValidationForm.route) {
            ValidationFormScreen()
        }
        
        composable(JCPDestination.ParentChildComponent.route) {
            ParentScreen()
        }
        
        composable(JCPDestination.Timer.route) {
            TimerScreen()
        }
        
        // JCP-03: Маршруты
        composable(JCPDestination.SimpleNavigation.route) {
            SimpleNavigationScreen()
        }
        
        composable(JCPDestination.NavigationWithParams.route) {
            NavigationWithParamsScreen()
        }
        
        composable(JCPDestination.NavigationWithObjects.route) {
            NavigationWithObjectsScreen()
        }
        
        composable(JCPDestination.NestedNavigation.route) {
            NestedNavigationScreen()
        }
        
        composable(JCPDestination.DeepLinks.route) {
            // TODO: Создать экран DeepLinksScreen
            DeepLinksScreen()
        }
        
        composable(JCPDestination.NavigationAnimations.route) {
            // TODO: Создать экран NavigationAnimationsScreen
            NavigationAnimationsScreen()
        }
        
        composable(JCPDestination.BackStackManagement.route) {
            // TODO: Создать экран BackStackManagementScreen
            BackStackManagementScreen()
        }
        
        // JCP-04: Маршруты
        composable(JCPDestination.BasicCoroutine.route) {
            BasicCoroutineScreen()
        }
        
        composable(JCPDestination.ParallelCoroutines.route) {
            ParallelCoroutinesScreen()
        }
        
        composable(JCPDestination.CoroutineLifecycle.route) {
            CoroutineLifecycleScreen()
        }
        
        composable(JCPDestination.BasicFlow.route) {
            BasicFlowScreen()
        }
        
        composable(JCPDestination.FlowErrorHandling.route) {
            FlowErrorHandlingScreen()
        }
        
        composable(JCPDestination.StateFlow.route) {
            StateFlowScreen()
        }
        
        composable(JCPDestination.SharedFlow.route) {
            SharedFlowScreen()
        }
        
        // JCP-05: Маршруты
        composable(JCPDestination.ProductsScreen.route) {
            ProductsScreen(
                uiState = ProductsUiState(
                    isLoading = false,
                    products = listOf(
                        ProductUiModel(1, "Молоко", "89.9", "Свежее"),
                        ProductUiModel(2, "Хлеб", "49.9", "Бородинский"),
                        ProductUiModel(3, "Сыр", "250.0", "Гауда")
                    )
                )
            )
        }
    }
} 