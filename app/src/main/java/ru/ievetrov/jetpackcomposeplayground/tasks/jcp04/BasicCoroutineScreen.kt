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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

@Composable
fun BasicCoroutineScreen() {
    JetpackComposePlaygroundTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            val scope = rememberCoroutineScope()
            var launchedEffectRunId by remember { mutableStateOf(0) }
            var isLoading by remember { mutableStateOf(false) }
            var progressMessage by remember { mutableStateOf("Корутину еще не запускали") }
            var result by remember { mutableStateOf("") }
            var errorMessage by remember { mutableStateOf<String?>(null) }

            suspend fun runDemoCoroutine(startedBy: String) {
                isLoading = true
                result = ""
                errorMessage = null

                try {
                    progressMessage = "Запуск: $startedBy"

                    val stepResult = performStepByStepOperation { step ->
                        progressMessage = step
                    }

                    val riskyResult = performOperationWithError()

                    result = "$stepResult\n$riskyResult"
                    progressMessage = "Готово"
                } catch (e: Exception) {
                    errorMessage = "Ошибка: ${e.message ?: "неизвестная ошибка"}"
                    progressMessage = "Операция завершилась с ошибкой"
                } finally {
                    isLoading = false
                }
            }

            LaunchedEffect(launchedEffectRunId) {
                if (launchedEffectRunId > 0) {
                    runDemoCoroutine("LaunchedEffect")
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "JCP-04: Базовая работа с корутинами",
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "Экран показывает два способа запуска: rememberCoroutineScope для событий пользователя и LaunchedEffect для запуска по изменению состояния.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        enabled = !isLoading,
                        onClick = {
                            scope.launch {
                                runDemoCoroutine("rememberCoroutineScope")
                            }
                        }
                    ) {
                        Text("Запустить корутину")
                    }

                    Button(
                        enabled = !isLoading,
                        onClick = { launchedEffectRunId++ }
                    ) {
                        Text("Через LaunchedEffect")
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isLoading) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.width(12.dp))
                    }

                    Text(text = progressMessage)
                }

                if (result.isNotBlank()) {
                    Text(
                        text = result,
                        color = Color(0xFF2E7D32),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                errorMessage?.let { message ->
                    Text(
                        text = message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "performOperationWithError() иногда специально выбрасывает исключение, чтобы показать обработку try-catch.",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

suspend fun performLongOperation(): String {
    delay(3000)
    return "Операция успешно завершена!"
}

suspend fun performOperationWithError(): String {
    delay(2000)

    if (Math.random() > 0.5) {
        throw RuntimeException("Случайная ошибка в операции")
    }

    return "Операция с риском завершена!"
}

suspend fun performStepByStepOperation(onProgress: (String) -> Unit): String {
    onProgress("Начинаем операцию...")
    delay(1000)

    onProgress("Выполняем шаг 1...")
    delay(1000)

    onProgress("Выполняем шаг 2...")
    delay(1000)

    onProgress("Завершаем операцию...")
    delay(500)

    return "Все шаги выполнены!"
}

@Preview(showBackground = true)
@Composable
fun BasicCoroutineScreenPreview() {
    BasicCoroutineScreen()
}