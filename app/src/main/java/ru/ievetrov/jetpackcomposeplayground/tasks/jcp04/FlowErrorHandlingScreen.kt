package ru.ievetrov.jetpackcomposeplayground.tasks.jcp04

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

@Composable
fun FlowErrorHandlingScreen() {
    JetpackComposePlaygroundTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            val scope = rememberCoroutineScope()

            var isRunning by remember { mutableStateOf(false) }
            var status by remember { mutableStateOf("Flow еще не запускался") }
            var errorMessage by remember { mutableStateOf<String?>(null) }
            var retryCount by remember { mutableStateOf(0) }
            var events by remember { mutableStateOf<List<String>>(emptyList()) }

            fun startFlow() {
                isRunning = true
                status = "Запуск Flow"
                errorMessage = null
                retryCount = 0
                events = emptyList()

                scope.launch {
                    failingNumbersFlow()
                        .onStart {
                            status = "Flow активен"
                            events = events + "Старт сбора"
                        }
                        .retry(retries = 2) { throwable ->
                            retryCount++
                            events = events + "Retry #$retryCount после ошибки: ${throwable.message}"
                            delay(700)
                            true
                        }
                        .catch { throwable ->
                            errorMessage = "Ошибка после повторных попыток: ${throwable.message}"
                            events = events + "catch обработал ошибку"

                            emit("Fallback значение после ошибки")
                        }
                        .onCompletion { cause ->
                            status = if (cause == null) {
                                "Flow завершен"
                            } else {
                                "Flow завершен с причиной: ${cause.message}"
                            }

                            isRunning = false
                        }
                        .collect { value ->
                            events = events + value
                        }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "JCP-04: Обработка ошибок в Flow",
                    style = MaterialTheme.typography.headlineMedium
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        enabled = !isRunning,
                        onClick = { startFlow() }
                    ) {
                        Text(
                            if (events.isEmpty()) {
                                "Запустить Flow"
                            } else {
                                "Перезапустить Flow"
                            }
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isRunning) {
                        CircularProgressIndicator()
                    } else {
                        Text("Flow не активен")
                    }
                }

                Text(text = "Состояние: $status")
                Text(text = "Количество повторных попыток: $retryCount")

                errorMessage?.let { message ->
                    Text(
                        text = message,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                ) {
                    items(events) { event ->
                        Text(
                            text = "• $event",
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Flow специально падает на третьем значении. retry делает две повторные попытки, catch показывает fallback, onCompletion фиксирует завершение.",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

private fun failingNumbersFlow(): Flow<String> = flow {
    for (i in 1..5) {
        delay(600)

        if (i == 3) {
            throw RuntimeException("Ошибка на элементе $i")
        }

        emit("Значение $i")
    }
}

@Preview(showBackground = true)
@Composable
fun FlowErrorHandlingScreenPreview() {
    FlowErrorHandlingScreen()
}