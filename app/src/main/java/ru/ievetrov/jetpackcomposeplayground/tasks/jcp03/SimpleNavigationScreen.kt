package ru.ievetrov.jetpackcomposeplayground.tasks.jcp03

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

@Composable
fun SimpleNavigationScreen() {
    JetpackComposePlaygroundTheme {
        val navController = rememberNavController()

        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Text(
                    text = "JCP-03: Базовая навигация",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                NavigationButtons(navController)

                NavHost(
                    navController = navController,
                    startDestination = "screen1"
                ) {
                    composable("screen1") { Screen1() }
                    composable("screen2") { Screen2() }
                    composable("screen3") { Screen3() }
                }
            }
        }
    }
}

@Composable
fun NavigationButtons(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Column(
        modifier = Modifier.padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate("screen1") },
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text("Перейти к экрану 1")
        }

        Button(
            onClick = { navController.navigate("screen2") },
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text("Перейти к экрану 2")
        }

        Button(
            onClick = { navController.navigate("screen3") },
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text("Перейти к экрану 3")
        }

        Button(
            onClick = { navController.popBackStack() },
            enabled = currentRoute != "screen1",
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text("Назад")
        }
    }
}

@Composable
fun Screen1() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Экран 1",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Содержимое первого экрана")
    }
}

@Composable
fun Screen2() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Экран 2",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Содержимое второго экрана")
    }
}

@Composable
fun Screen3() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Экран 3",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Содержимое третьего экрана")
    }
}

@Preview(showBackground = true)
@Composable
fun SimpleNavigationScreenPreview() {
    SimpleNavigationScreen()
}