package ru.ievetrov.jetpackcomposeplayground.tasks.jcp03

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

@Composable
fun NestedNavigationScreen() {
    JetpackComposePlaygroundTheme {
        val navController = rememberNavController()

        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Text(
                    text = "JCP-03: Вложенная навигация",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = BottomNavItem.Home.route,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        composable(BottomNavItem.Home.route) {
                            HomeNavGraph(
                                modifier = Modifier.fillMaxSize(),
                                navigateToProfile = {
                                    navController.navigate(BottomNavItem.Profile.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }

                        composable(BottomNavItem.Favorites.route) {
                            FavoritesNavGraph(
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        composable(BottomNavItem.Profile.route) {
                            ProfileNavGraph(
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Элементы нижней навигации
 */
sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Главная")
    object Favorites : BottomNavItem("favorites", Icons.Default.Favorite, "Избранное")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Профиль")
}

/**
 * Компонент нижней навигации
 */
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favorites,
        BottomNavItem.Profile
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

/**
 * Вложенный навигационный граф для раздела "Главная"
 */
@Composable
fun HomeNavGraph(
    modifier: Modifier = Modifier,
    navigateToProfile: () -> Unit = {}
) {
    val homeNavController = rememberNavController()

    NavHost(
        navController = homeNavController,
        startDestination = "home_main",
        modifier = modifier
    ) {
        composable("home_main") {
            HomeMainScreen(
                onDetailsClick = { homeNavController.navigate("home_details") },
                onNavigateToProfile = navigateToProfile
            )
        }

        composable("home_details") {
            HomeDetailsScreen(
                onBackClick = { homeNavController.popBackStack() }
            )
        }
    }
}

/**
 * Вложенный навигационный граф для раздела "Избранное"
 */
@Composable
fun FavoritesNavGraph(modifier: Modifier = Modifier) {
    val favoritesNavController = rememberNavController()

    NavHost(
        navController = favoritesNavController,
        startDestination = "favorites_main",
        modifier = modifier
    ) {
        composable("favorites_main") {
            FavoritesMainScreen(
                onDetailsClick = { favoritesNavController.navigate("favorites_details") }
            )
        }

        composable("favorites_details") {
            FavoritesDetailsScreen(
                onBackClick = { favoritesNavController.popBackStack() }
            )
        }
    }
}

/**
 * Вложенный навигационный граф для раздела "Профиль"
 */
@Composable
fun ProfileNavGraph(modifier: Modifier = Modifier) {
    val profileNavController = rememberNavController()

    NavHost(
        navController = profileNavController,
        startDestination = "profile_main",
        modifier = modifier
    ) {
        composable("profile_main") {
            ProfileMainScreen(
                onSettingsClick = { profileNavController.navigate("profile_settings") }
            )
        }

        composable("profile_settings") {
            ProfileSettingsScreen(
                onBackClick = { profileNavController.popBackStack() }
            )
        }
    }
}

@Composable
fun HomeMainScreen(
    onDetailsClick: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Главная страница",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onDetailsClick,
            modifier = Modifier.fillMaxSize(0.7f)
        ) {
            Text("Открыть детали")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToProfile,
            modifier = Modifier.fillMaxSize(0.7f)
        ) {
            Text("Перейти к профилю")
        }
    }
}

@Composable
fun HomeDetailsScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Детали раздела Главная",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBackClick
        ) {
            Text("Назад")
        }
    }
}

@Composable
fun FavoritesMainScreen(onDetailsClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Избранное",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onDetailsClick
        ) {
            Text("Открыть детали")
        }
    }
}

@Composable
fun FavoritesDetailsScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Детали избранного",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBackClick
        ) {
            Text("Назад")
        }
    }
}

@Composable
fun ProfileMainScreen(onSettingsClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Профиль пользователя",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSettingsClick
        ) {
            Text("Настройки")
        }
    }
}

@Composable
fun ProfileSettingsScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Настройки профиля",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBackClick
        ) {
            Text("Назад")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NestedNavigationScreenPreview() {
    NestedNavigationScreen()
}