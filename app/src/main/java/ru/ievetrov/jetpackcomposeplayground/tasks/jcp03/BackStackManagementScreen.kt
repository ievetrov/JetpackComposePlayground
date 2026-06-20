package ru.ievetrov.jetpackcomposeplayground.tasks.jcp03

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

private const val HOME_ROUTE = "home"
private const val SCREEN_1_ROUTE = "screen1"
private const val SCREEN_2_ROUTE = "screen2"
private const val SCREEN_3_ROUTE = "screen3"
private const val SCREEN_4_ROUTE = "screen4"

private val screenOrder = listOf(
    HOME_ROUTE,
    SCREEN_1_ROUTE,
    SCREEN_2_ROUTE,
    SCREEN_3_ROUTE,
    SCREEN_4_ROUTE
)

private val screenTitles = mapOf(
    HOME_ROUTE to "Главный экран",
    SCREEN_1_ROUTE to "Экран 1",
    SCREEN_2_ROUTE to "Экран 2",
    SCREEN_3_ROUTE to "Экран 3",
    SCREEN_4_ROUTE to "Экран 4"
)

@Composable
fun BackStackManagementScreen() {
    JetpackComposePlaygroundTheme {
        val navController = rememberNavController()
        var backStackRoutes by remember { mutableStateOf(listOf(HOME_ROUTE)) }

        fun syncStack(newStack: List<String>) {
            backStackRoutes = newStack
        }

        fun navigateForward() {
            val currentRoute = backStackRoutes.lastOrNull() ?: HOME_ROUTE
            val currentIndex = screenOrder.indexOf(currentRoute)
            val nextRoute = screenOrder.getOrNull(currentIndex + 1) ?: return

            navController.navigate(nextRoute)
            syncStack(backStackRoutes + nextRoute)
        }

        fun navigateBack() {
            if (backStackRoutes.size > 1) {
                navController.popBackStack()
                syncStack(backStackRoutes.dropLast(1))
            }
        }

        fun navigateHomeClearingAll() {
            navController.navigate(HOME_ROUTE) {
                popUpTo(HOME_ROUTE) {
                    inclusive = true
                }
                launchSingleTop = true
            }
            syncStack(listOf(HOME_ROUTE))
        }

        fun navigateToScreen3ClearingIntermediate() {
            navController.navigate(SCREEN_3_ROUTE) {
                popUpTo(HOME_ROUTE) {
                    inclusive = false
                }
                launchSingleTop = true
            }
            syncStack(listOf(HOME_ROUTE, SCREEN_3_ROUTE))
        }

        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = currentBackStackEntry?.destination?.route ?: HOME_ROUTE

        BackHandler(enabled = backStackRoutes.size > 1) {
            navigateBack()
        }

        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Text(
                    text = "JCP-03: Управление бэкстеком",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                NavHost(
                    navController = navController,
                    startDestination = HOME_ROUTE
                ) {
                    composable(HOME_ROUTE) {
                        BackStackDemoContent(
                            currentScreenTitle = screenTitles[HOME_ROUTE] ?: HOME_ROUTE,
                            backStackText = backStackRoutes.joinToString(
                                prefix = "[",
                                postfix = "]"
                            ) { route -> screenTitles[route] ?: route },
                            nextScreenTitle = screenTitles[SCREEN_1_ROUTE],
                            canGoForward = true,
                            canGoBack = false,
                            onForwardClick = { navigateForward() },
                            onJumpToScreen3Click = { navigateToScreen3ClearingIntermediate() },
                            onHomeClick = { navigateHomeClearingAll() },
                            onBackClick = { navigateBack() }
                        )
                    }

                    composable(SCREEN_1_ROUTE) {
                        BackStackDemoContent(
                            currentScreenTitle = screenTitles[SCREEN_1_ROUTE] ?: SCREEN_1_ROUTE,
                            backStackText = backStackRoutes.joinToString(
                                prefix = "[",
                                postfix = "]"
                            ) { route -> screenTitles[route] ?: route },
                            nextScreenTitle = screenTitles[SCREEN_2_ROUTE],
                            canGoForward = true,
                            canGoBack = true,
                            onForwardClick = { navigateForward() },
                            onJumpToScreen3Click = { navigateToScreen3ClearingIntermediate() },
                            onHomeClick = { navigateHomeClearingAll() },
                            onBackClick = { navigateBack() }
                        )
                    }

                    composable(SCREEN_2_ROUTE) {
                        BackStackDemoContent(
                            currentScreenTitle = screenTitles[SCREEN_2_ROUTE] ?: SCREEN_2_ROUTE,
                            backStackText = backStackRoutes.joinToString(
                                prefix = "[",
                                postfix = "]"
                            ) { route -> screenTitles[route] ?: route },
                            nextScreenTitle = screenTitles[SCREEN_3_ROUTE],
                            canGoForward = true,
                            canGoBack = true,
                            onForwardClick = { navigateForward() },
                            onJumpToScreen3Click = { navigateToScreen3ClearingIntermediate() },
                            onHomeClick = { navigateHomeClearingAll() },
                            onBackClick = { navigateBack() }
                        )
                    }

                    composable(SCREEN_3_ROUTE) {
                        BackStackDemoContent(
                            currentScreenTitle = screenTitles[SCREEN_3_ROUTE] ?: SCREEN_3_ROUTE,
                            backStackText = backStackRoutes.joinToString(
                                prefix = "[",
                                postfix = "]"
                            ) { route -> screenTitles[route] ?: route },
                            nextScreenTitle = screenTitles[SCREEN_4_ROUTE],
                            canGoForward = true,
                            canGoBack = true,
                            onForwardClick = { navigateForward() },
                            onJumpToScreen3Click = { navigateToScreen3ClearingIntermediate() },
                            onHomeClick = { navigateHomeClearingAll() },
                            onBackClick = { navigateBack() }
                        )
                    }

                    composable(SCREEN_4_ROUTE) {
                        BackStackDemoContent(
                            currentScreenTitle = screenTitles[SCREEN_4_ROUTE] ?: SCREEN_4_ROUTE,
                            backStackText = backStackRoutes.joinToString(
                                prefix = "[",
                                postfix = "]"
                            ) { route -> screenTitles[route] ?: route },
                            nextScreenTitle = null,
                            canGoForward = false,
                            canGoBack = true,
                            onForwardClick = { navigateForward() },
                            onJumpToScreen3Click = { navigateToScreen3ClearingIntermediate() },
                            onHomeClick = { navigateHomeClearingAll() },
                            onBackClick = { navigateBack() }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Текущий route: $currentRoute",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun BackStackDemoContent(
    currentScreenTitle: String,
    backStackText: String,
    nextScreenTitle: String?,
    canGoForward: Boolean,
    canGoBack: Boolean,
    onForwardClick: () -> Unit,
    onJumpToScreen3Click: () -> Unit,
    onHomeClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Демонстрация управления бэкстеком",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Текущий экран: $currentScreenTitle",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Состояние бэкстека: $backStackText",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onForwardClick,
            enabled = canGoForward,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = if (nextScreenTitle != null) {
                    "Перейти вперёд: $nextScreenTitle"
                } else {
                    "Дальше нет экранов"
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onJumpToScreen3Click,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Перейти на экран 3 (очистив промежуточные)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onHomeClick,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Вернуться на главный экран")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBackClick,
            enabled = canGoBack,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Назад")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BackStackManagementScreenPreview() {
    BackStackManagementScreen()
}