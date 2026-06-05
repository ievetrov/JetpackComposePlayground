package ru.ievetrov.jetpackcomposeplayground.tasks.jcp02

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

/**
 * JCP-02: Подъём состояния (State Hoisting) - Родительский экран
 *
 * Задание:
 * 1. Реализовать компонент-потомок с интерактивными элементами
 * 2. Вынести состояние в родительский компонент
 * 3. Передавать значение и обработчики через параметры
 * 4. Обеспечить двустороннюю связь состояний между родителем и потомком
 */

@Composable
fun ParentScreen() {
    var text by remember { mutableStateOf("") }
    var counter by remember { mutableIntStateOf(0) }
    var isEnabled by remember { mutableStateOf(true) }

    JetpackComposePlaygroundTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Text(
                    text = "JCP-02: Подъём состояния",
                    style = MaterialTheme.typography.headlineMedium
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                Text(
                    text = "Текст: $text",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Счётчик: $counter",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Активен: ${if (isEnabled) "Да" else "Нет"}",
                    style = MaterialTheme.typography.bodyLarge
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                ChildComponent(
                    value = text,
                    onValueChange = { text = it },
                    counterValue = counter,
                    onIncrementCounter = { counter++ },
                    isEnabled = isEnabled,
                    onToggleEnabled = { isEnabled = !isEnabled }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParentScreenPreview() {
    ParentScreen()
}