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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme
/**
 * JCP-02: Простое состояние кнопок
 *
 * Задание:
 * 1. Реализовать две кнопки с общим счётчиком нажатий
 * 2. Увеличивать счётчик на 1 при нажатии первой кнопки
 * 3. Уменьшать счётчик на 1 при нажатии второй кнопки
 * 4. Отображать текущее значение счётчика между кнопками
 * 5. Предотвратить возможность ухода счётчика в отрицательные значения
 */

@Composable
fun SimpleButtonsScreen() {
    var count by remember { mutableStateOf(0) }

    JetpackComposePlaygroundTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column (horizontalAlignment = Alignment.CenterHorizontally)
            {
                Text(
                    text = "JCP-02: Простое состояние кнопок",
                    style = MaterialTheme.typography.headlineMedium
                )
                
                // TODO 1: Реализовать две кнопки с общим счётчиком нажатий
                // Используйте: var count by remember { mutableStateOf(0) }




                // TODO 2: Увеличивать счётчик на 1 при нажатии первой кнопки
                // Button(onClick = { count++ }) { Text("+1") }
                
                // TODO 3: Уменьшать счётчик на 1 при нажатии второй кнопки
                // Button(onClick = { count-- }) { Text("-1") }
                
                // TODO 4: Отображать текущее значение счётчика между кнопками
                // Text("Счёт: $count", style = MaterialTheme.typography.headlineMedium)
                
                // TODO 5: Предотвратить возможность ухода счётчика в отрицательные значения
                // Добавьте проверку: if (count > 0) count--

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { count++ }
                    ) {
                        Text("+1")
                    }

                    Text(
                        text = "Счёт: $count",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Button(
                        onClick = {
                            if (count > 0) {
                                count--
                            }
                        }
                    ) {
                        Text("-1")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SimpleButtonsScreenPreview() {
    SimpleButtonsScreen()
} 