package ru.ievetrov.jetpackcomposeplayground.tasks.jcp01

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

@Composable
fun SimpleStateScreen() {
    var text by remember { mutableStateOf("") }
    var accentMode by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }

    val cardColor = if (accentMode) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    val previewTextColor = if (isChecked) {
        Color.Red
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "JCP-01: Состояние в Compose",
                style = MaterialTheme.typography.headlineMedium
            )

            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Введите текст") }
            )

            Text(
                text = if (text.isBlank()) {
                    "Текст появится здесь в реальном времени"
                } else {
                    "Сейчас введено: $text"
                },
                style = MaterialTheme.typography.bodyLarge
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Switch меняет внешний вид карточки")
                    Text(
                        text = if (accentMode) "Акцентный режим включён" else "Акцентный режим выключен",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Switch(
                    checked = accentMode,
                    onCheckedChange = { accentMode = it }
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it }
                )

                Text(
                    text = "Checkbox меняет цвет текста",
                    color = previewTextColor
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardColor)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Превью состояния",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = if (text.isBlank()) "Здесь будет ваш текст" else text,
                        color = previewTextColor,
                        fontSize = if (accentMode) 22.sp else 18.sp,
                        fontWeight = if (isChecked) FontWeight.Bold else FontWeight.Normal
                    )

                    Text(
                        text = "Switch: ${if (accentMode) "ON" else "OFF"} • Checkbox: ${if (isChecked) "ON" else "OFF"}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SimpleStateScreenPreview() {
    JetpackComposePlaygroundTheme {
        SimpleStateScreen()
    }
}