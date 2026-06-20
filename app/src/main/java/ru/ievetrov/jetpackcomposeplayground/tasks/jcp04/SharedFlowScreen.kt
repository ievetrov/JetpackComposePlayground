package ru.ievetrov.jetpackcomposeplayground.tasks.jcp04

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

@Composable
fun SharedFlowScreen() {
    JetpackComposePlaygroundTheme {
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        val events = remember { MutableSharedFlow<AppEvent>(extraBufferCapacity = 1) }

        var dialogMessage by remember { mutableStateOf<String?>(null) }
        var lastAction by remember { mutableStateOf("Событий пока нет") }

        LaunchedEffect(events) {
            events.collect { event ->
                when (event) {
                    is AppEvent.Message -> {
                        lastAction = "Сообщение: ${event.text}"
                        snackbarHostState.showSnackbar(event.text)
                    }

                    is AppEvent.Error -> {
                        lastAction = "Ошибка: ${event.text}"
                        dialogMessage = event.text
                    }

                    is AppEvent.Navigation -> {
                        lastAction = "Подготовка навигации: ${event.destination}"

                        val result = snackbarHostState.showSnackbar(
                            message = "Перейти на экран: ${event.destination}?",
                            actionLabel = "Отменить",
                            duration = SnackbarDuration.Short
                        )

                        lastAction = if (result == SnackbarResult.ActionPerformed) {
                            snackbarHostState.showSnackbar("Действие отменено")
                            "Навигация отменена"
                        } else {
                            "Навигация выполнена: ${event.destination}"
                        }
                    }
                }
            }
        }

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "JCP-04: События с SharedFlow",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Text(
                        text = "MutableSharedFlow используется как шина одноразовых событий UI.",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                scope.launch {
                                    events.emit(AppEvent.Message("Данные успешно сохранены"))
                                }
                            }
                        ) {
                            Text("Сообщение")
                        }

                        Button(
                            onClick = {
                                scope.launch {
                                    events.emit(AppEvent.Error("Не удалось синхронизировать данные"))
                                }
                            }
                        ) {
                            Text("Ошибка")
                        }
                    }

                    Button(
                        onClick = {
                            scope.launch {
                                events.emit(AppEvent.Navigation("Детали заказа"))
                            }
                        }
                    ) {
                        Text("Навигация с отменой")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Последнее действие: $lastAction",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        dialogMessage?.let { message ->
            AlertDialog(
                onDismissRequest = {
                    dialogMessage = null
                },
                title = {
                    Text("Ошибка")
                },
                text = {
                    Text(message)
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            dialogMessage = null
                        }
                    ) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

sealed class AppEvent {
    data class Message(val text: String) : AppEvent()
    data class Error(val text: String) : AppEvent()
    data class Navigation(val destination: String) : AppEvent()
}

@Preview(showBackground = true)
@Composable
fun SharedFlowScreenPreview() {
    SharedFlowScreen()
}