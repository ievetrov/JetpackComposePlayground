package ru.ievetrov.jetpackcomposeplayground.tasks.jcp04

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

@Composable
fun CoroutineLifecycleScreen() {
    JetpackComposePlaygroundTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            val scope = rememberCoroutineScope()

            var elapsedMs by remember { mutableStateOf(0L) }
            var isRunning by remember { mutableStateOf(false) }
            var timerJob by remember { mutableStateOf<Job?>(null) }

            fun pauseTimer() {
                timerJob?.cancel()
                timerJob = null
                isRunning = false
            }

            fun startTimer() {
                if (timerJob?.isActive == true) return

                isRunning = true

                timerJob = scope.launch {
                    while (isActive) {
                        delay(100)
                        elapsedMs += 100
                    }
                }
            }

            fun resetTimer() {
                pauseTimer()
                elapsedMs = 0L
            }

            DisposableEffect(Unit) {
                onDispose {
                    timerJob?.cancel()
                }
            }

            val seconds = elapsedMs / 1000
            val tenths = (elapsedMs % 1000) / 100
            val progress = ((elapsedMs % 10_000).toFloat() / 10_000f).coerceIn(0f, 1f)

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "JCP-04: Управление жизненным циклом корутин",
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "Таймер: $seconds.$tenths сек",
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = if (isRunning) "Состояние: работает" else "Состояние: пауза",
                    style = MaterialTheme.typography.bodyLarge
                )

                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        enabled = !isRunning,
                        onClick = { startTimer() }
                    ) {
                        Text("Старт")
                    }

                    Button(
                        enabled = isRunning,
                        onClick = { pauseTimer() }
                    ) {
                        Text("Пауза")
                    }

                    Button(onClick = { resetTimer() }) {
                        Text("Сброс")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "DisposableEffect отменяет корутину при уходе экрана из композиции, а цикл проверяет isActive перед каждым следующим шагом.",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoroutineLifecycleScreenPreview() {
    CoroutineLifecycleScreen()
}