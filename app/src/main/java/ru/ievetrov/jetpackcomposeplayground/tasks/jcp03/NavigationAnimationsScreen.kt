package ru.ievetrov.jetpackcomposeplayground.tasks.jcp03

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
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
import androidx.navigation.compose.rememberNavController
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

enum class AnimationType {
    FADE, SLIDE, SCALE, COMBINED
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NavigationAnimationsScreen() {
    JetpackComposePlaygroundTheme {
        val navController = rememberNavController()
        var selectedAnimationType by remember { mutableStateOf(AnimationType.FADE) }

        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "JCP-03: Анимации переходов",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Выберите тип анимации:",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AnimationType.values().forEach { type ->
                        FilterChip(
                            selected = selectedAnimationType == type,
                            onClick = { selectedAnimationType = type },
                            label = { Text(type.name) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                NavHost(
                    navController = navController,
                    startDestination = "home",
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(
                        route = "home",
                        enterTransition = {
                            animationEnter(selectedAnimationType, isPop = false)
                        },
                        exitTransition = {
                            animationExit(selectedAnimationType, isPop = false)
                        },
                        popEnterTransition = {
                            animationEnter(selectedAnimationType, isPop = true)
                        },
                        popExitTransition = {
                            animationExit(selectedAnimationType, isPop = true)
                        }
                    ) {
                        AnimationHomeScreen(
                            selectedAnimationType = selectedAnimationType,
                            onDetailsClick = { navController.navigate("details") },
                            onProfileClick = { navController.navigate("profile") }
                        )
                    }

                    composable(
                        route = "details",
                        enterTransition = {
                            animationEnter(selectedAnimationType, isPop = false)
                        },
                        exitTransition = {
                            animationExit(selectedAnimationType, isPop = false)
                        },
                        popEnterTransition = {
                            animationEnter(selectedAnimationType, isPop = true)
                        },
                        popExitTransition = {
                            animationExit(selectedAnimationType, isPop = true)
                        }
                    ) {
                        AnimationDetailsScreen(
                            selectedAnimationType = selectedAnimationType,
                            onNextClick = { navController.navigate("profile") },
                            onBackClick = { navController.popBackStack() }
                        )
                    }

                    composable(
                        route = "profile",
                        enterTransition = {
                            animationEnter(selectedAnimationType, isPop = false)
                        },
                        exitTransition = {
                            animationExit(selectedAnimationType, isPop = false)
                        },
                        popEnterTransition = {
                            animationEnter(selectedAnimationType, isPop = true)
                        },
                        popExitTransition = {
                            animationExit(selectedAnimationType, isPop = true)
                        }
                    ) {
                        AnimationProfileScreen(
                            selectedAnimationType = selectedAnimationType,
                            onBackClick = { navController.popBackStack() },
                            onHomeClick = {
                                navController.navigate("home") {
                                    popUpTo("home") { inclusive = false }
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

fun animationEnter(
    animationType: AnimationType,
    isPop: Boolean
): EnterTransition {
    return when (animationType) {
        AnimationType.FADE -> fadeIn()
        AnimationType.SLIDE -> {
            if (isPop) {
                slideInHorizontally(initialOffsetX = { -it })
            } else {
                slideInHorizontally(initialOffsetX = { it })
            }
        }
        AnimationType.SCALE -> scaleIn()
        AnimationType.COMBINED -> {
            if (isPop) {
                fadeIn() + slideInHorizontally(initialOffsetX = { -it }) + scaleIn()
            } else {
                fadeIn() + slideInHorizontally(initialOffsetX = { it }) + scaleIn()
            }
        }
    }
}

fun animationExit(
    animationType: AnimationType,
    isPop: Boolean
): ExitTransition {
    return when (animationType) {
        AnimationType.FADE -> fadeOut()
        AnimationType.SLIDE -> {
            if (isPop) {
                slideOutHorizontally(targetOffsetX = { it })
            } else {
                slideOutHorizontally(targetOffsetX = { -it })
            }
        }
        AnimationType.SCALE -> scaleOut()
        AnimationType.COMBINED -> {
            if (isPop) {
                fadeOut() + slideOutHorizontally(targetOffsetX = { it }) + scaleOut()
            } else {
                fadeOut() + slideOutHorizontally(targetOffsetX = { -it }) + scaleOut()
            }
        }
    }
}

@Composable
fun AnimationHomeScreen(
    selectedAnimationType: AnimationType,
    onDetailsClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Главный экран",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Текущая анимация: ${selectedAnimationType.name}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onDetailsClick,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Открыть Details")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onProfileClick,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Открыть Profile")
        }
    }
}

@Composable
fun AnimationDetailsScreen(
    selectedAnimationType: AnimationType,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Экран Details",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Текущая анимация: ${selectedAnimationType.name}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onNextClick,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Перейти к Profile")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Назад")
        }
    }
}

@Composable
fun AnimationProfileScreen(
    selectedAnimationType: AnimationType,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Экран Profile",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Текущая анимация: ${selectedAnimationType.name}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onHomeClick,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("На главный экран")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Назад")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationAnimationsScreenPreview() {
    NavigationAnimationsScreen()
}