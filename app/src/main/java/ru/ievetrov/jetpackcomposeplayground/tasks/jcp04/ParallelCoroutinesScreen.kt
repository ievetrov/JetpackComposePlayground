package ru.ievetrov.jetpackcomposeplayground.tasks.jcp04

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

@Composable
fun ParallelCoroutinesScreen() {
    JetpackComposePlaygroundTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            val scope = rememberCoroutineScope()

            var isSourceOneLoading by remember { mutableStateOf(false) }
            var isSourceTwoLoading by remember { mutableStateOf(false) }

            var sourceOneResult by remember { mutableStateOf<SourceLoadResult?>(null) }
            var sourceTwoResult by remember { mutableStateOf<SourceLoadResult?>(null) }

            var combinedResult by remember { mutableStateOf("") }
            var totalTimeMs by remember { mutableStateOf<Long?>(null) }
            var errorMessage by remember { mutableStateOf<String?>(null) }

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "JCP-04: Параллельные операции",
                    style = MaterialTheme.typography.headlineMedium
                )

                Button(
                    enabled = !isSourceOneLoading && !isSourceTwoLoading,
                    onClick = {
                        scope.launch {
                            sourceOneResult = null
                            sourceTwoResult = null
                            combinedResult = ""
                            totalTimeMs = null
                            errorMessage = null

                            val totalStartedAt = System.currentTimeMillis()

                            try {
                                coroutineScope {
                                    val sourceOneDeferred = async {
                                        isSourceOneLoading = true

                                        try {
                                            loadFromSourceOne()
                                        } finally {
                                            isSourceOneLoading = false
                                        }
                                    }

                                    val sourceTwoDeferred = async {
                                        isSourceTwoLoading = true

                                        try {
                                            loadFromSourceTwo()
                                        } finally {
                                            isSourceTwoLoading = false
                                        }
                                    }

                                    val first = sourceOneDeferred.await()
                                    val second = sourceTwoDeferred.await()

                                    sourceOneResult = first
                                    sourceTwoResult = second
                                    combinedResult = "${first.data} + ${second.data}"
                                }
                            } catch (e: Exception) {
                                errorMessage = "Ошибка загрузки: ${e.message ?: "неизвестная ошибка"}"
                            } finally {
                                totalTimeMs = System.currentTimeMillis() - totalStartedAt
                            }
                        }
                    }
                ) {
                    Text("Загрузить данные")
                }

                SourceStatusRow(
                    title = "Источник 1",
                    isLoading = isSourceOneLoading,
                    result = sourceOneResult
                )

                SourceStatusRow(
                    title = "Источник 2",
                    isLoading = isSourceTwoLoading,
                    result = sourceTwoResult
                )

                if (combinedResult.isNotBlank()) {
                    Text(
                        text = "Объединенный результат: $combinedResult",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                totalTimeMs?.let { millis ->
                    Text(
                        text = "Общее время выполнения: $millis мс",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                errorMessage?.let { message ->
                    Text(
                        text = message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
private fun SourceStatusRow(
    title: String,
    isLoading: Boolean,
    result: SourceLoadResult?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isLoading) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.width(12.dp))
        }

        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = when {
                    isLoading -> "Загрузка..."
                    result != null -> "${result.data}, время: ${result.timeMs} мс"
                    else -> "Ожидает запуска"
                },
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}

data class SourceLoadResult(
    val data: String,
    val timeMs: Long
)

private suspend fun loadFromSourceOne(): SourceLoadResult {
    val startedAt = System.currentTimeMillis()

    delay(1800)

    return SourceLoadResult(
        data = "Профиль пользователя",
        timeMs = System.currentTimeMillis() - startedAt
    )
}

private suspend fun loadFromSourceTwo(): SourceLoadResult {
    val startedAt = System.currentTimeMillis()

    delay(2600)

    return SourceLoadResult(
        data = "Список заказов",
        timeMs = System.currentTimeMillis() - startedAt
    )
}

@Preview(showBackground = true)
@Composable
fun ParallelCoroutinesScreenPreview() {
    ParallelCoroutinesScreen()
}