package ru.ievetrov.jetpackcomposeplayground.tasks.jcp02

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

/**
 * JCP-02: Подъём состояния (State Hoisting) - Компонент ребенка
 *
 * Обратите внимание: Этот компонент не должен содержать собственное состояние.
 * Вместо этого он должен принимать значение и функцию обратного вызова от родителя.
 */

@Composable
fun ChildComponent(
    value: String,
    onValueChange: (String) -> Unit,
    counterValue: Int,
    onIncrementCounter: () -> Unit,
    isEnabled: Boolean,
    onToggleEnabled: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Компонент-потомок",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text("Введите текст") },
            enabled = isEnabled
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Счётчик: $counterValue",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = onIncrementCounter,
                enabled = isEnabled
            ) {
                Text("+1")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isEnabled) "Компонент активен" else "Компонент выключен",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.width(12.dp))

            Switch(
                checked = isEnabled,
                onCheckedChange = { onToggleEnabled() }
            )
        }
    }
}

@Preview
@Composable
fun ChildComponentPreview() {
    JetpackComposePlaygroundTheme {
        ChildComponent(
            value = "Тестовое значение",
            onValueChange = {},
            counterValue = 5,
            onIncrementCounter = {},
            isEnabled = true,
            onToggleEnabled = {}
        )
    }
}