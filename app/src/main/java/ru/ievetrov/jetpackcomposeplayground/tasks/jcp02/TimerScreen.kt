package ru.ievetrov.jetpackcomposeplayground.tasks.jcp02

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

/**
 * JCP-02: Таймер с состоянием
 *
 * Задание:
 * 1. Реализовать экран с секундомером
 * 2. Добавить кнопки "Старт", "Пауза", "Сброс"
 * 3. Отображать время в формате MM:SS.ms
 * 4. Использовать `remember` и простое состояние для времени
 */

fun formatTime(timeMillis: Long): String {
    val minutes = (timeMillis / 1000 / 60).toString().padStart(2, '0')
    val seconds = ((timeMillis / 1000) % 60).toString().padStart(2, '0')
    val millis = ((timeMillis % 1000) / 10).toString().padStart(2, '0')
    return "$minutes:$seconds.$millis"
}

@Composable
fun TimerScreen() {
    var elapsedTime by remember { mutableLongStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(10L)
            elapsedTime += 10L
        }
    }

    JetpackComposePlaygroundTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Text(
                    text = "JCP-02: Таймер с состоянием",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = formatTime(elapsedTime),
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { isRunning = true },
                        enabled = !isRunning
                    ) {
                        Text("Старт")
                    }

                    Button(
                        onClick = { isRunning = false },
                        enabled = isRunning
                    ) {
                        Text("Пауза")
                    }

                    Button(
                        onClick = {
                            elapsedTime = 0L
                            isRunning = false
                        }
                    ) {
                        Text("Сброс")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    TimerScreen()
}