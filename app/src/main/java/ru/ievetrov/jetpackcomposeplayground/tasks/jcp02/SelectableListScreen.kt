package ru.ievetrov.jetpackcomposeplayground.tasks.jcp02

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

/**
 * JCP-02: Список с выбором
 *
 * Задание:
 * 1. Реализовать список элементов с чекбоксами
 * 2. Обеспечить возможность выбора каждого элемента
 * 3. Отображать количество выбранных элементов под списком
 * 4. Добавить кнопку "Выбрать все" / "Снять выбор"
 */

data class SelectableItem(
    val id: Int,
    val title: String,
    var isSelected: Boolean = false
)

val sampleSelectableItems = listOf(
    SelectableItem(1, "Яблоки"),
    SelectableItem(2, "Молоко"),
    SelectableItem(3, "Хлеб"),
    SelectableItem(4, "Яйца"),
    SelectableItem(5, "Масло"),
    SelectableItem(6, "Сыр")
)

@Composable
fun SelectableItemRow(
    item: SelectableItem,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = item.isSelected,
            onCheckedChange = onCheckedChange
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun SelectableListScreen() {
    val items = remember {
        mutableStateListOf<SelectableItem>().apply {
            addAll(sampleSelectableItems)
        }
    }

    val selectedCount = items.count { it.isSelected }
    val allSelected = items.isNotEmpty() && items.all { it.isSelected }

    JetpackComposePlaygroundTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Text(
                    text = "JCP-02: Список с выбором",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(items) { item ->
                        SelectableItemRow(
                            item = item,
                            onCheckedChange = { isChecked ->
                                val index = items.indexOfFirst { it.id == item.id }
                                if (index != -1) {
                                    items[index] = items[index].copy(isSelected = isChecked)
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Выбрано: $selectedCount из ${items.size}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val newState = !allSelected
                        for (i in items.indices) {
                            items[i] = items[i].copy(isSelected = newState)
                        }
                    }
                ) {
                    Text(if (allSelected) "Снять выбор" else "Выбрать все")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectableListScreenPreview() {
    SelectableListScreen()
}