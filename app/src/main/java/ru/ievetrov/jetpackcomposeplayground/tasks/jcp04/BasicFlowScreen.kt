package ru.ievetrov.jetpackcomposeplayground.tasks.jcp04

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

@Composable
fun BasicFlowScreen() {
    JetpackComposePlaygroundTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            val scope = rememberCoroutineScope()

            var items by remember { mutableStateOf<List<String>>(emptyList()) }
            var isCollecting by remember { mutableStateOf(false) }
            var collectionJob by remember { mutableStateOf<Job?>(null) }

            fun stopCollecting() {
                collectionJob?.cancel()
                collectionJob = null
                isCollecting = false
            }

            DisposableEffect(Unit) {
                onDispose {
                    stopCollecting()
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "JCP-04: Создание и сбор Flow",
                    style = MaterialTheme.typography.headlineMedium
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        enabled = !isCollecting,
                        onClick = {
                            stopCollecting()
                            items = emptyList()
                            isCollecting = true

                            collectionJob = scope.launch {
                                createNumbersFlow()
                                    .map { number -> number * 2 }
                                    .filter { number -> number % 4 == 0 }
                                    .take(5)
                                    .onCompletion {
                                        isCollecting = false
                                        collectionJob = null
                                    }
                                    .collect { transformedNumber ->
                                        items = items + "Получено: $transformedNumber"
                                    }
                            }
                        }
                    ) {
                        Text("Запустить сбор")
                    }

                    Button(
                        enabled = isCollecting,
                        onClick = { stopCollecting() }
                    ) {
                        Text("Остановить")
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isCollecting) {
                        CircularProgressIndicator()
                    } else {
                        Text("Flow не активен")
                    }
                }

                Text(
                    text = "Трансформация: число * 2 → только кратные 4 → первые 5 значений",
                    style = MaterialTheme.typography.bodySmall
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                ) {
                    items(items) { item ->
                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

fun createNumbersFlow(): Flow<Int> = flow {
    for (i in 1..10) {
        delay(700)
        emit(i)
    }
}

fun createFilteredFlow(): Flow<Int> = flow {
    for (i in 1..20) {
        delay(500)
        emit(i)
    }
}.filter { it % 2 == 0 }

fun createErrorFlow(): Flow<String> = flow {
    emit("Пункт 1")
    delay(1000)

    emit("Пункт 2")
    delay(1000)

    throw RuntimeException("Ошибка в Flow!")
}

@Preview(showBackground = true)
@Composable
fun BasicFlowScreenPreview() {
    BasicFlowScreen()
}