package ru.ievetrov.jetpackcomposeplayground.tasks.jcp01

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

/**
 * JCP-01: Декларативные компоненты
 *
 * Задание:
 * 1. Создать композабл-функцию, отображающую приветствие с передаваемым именем
 * 2. Добавить к тексту модификаторы (padding, background, размер)
 * 3. Реализовать предпросмотр для компонента с разными входными данными
 * 4. Продемонстрировать преимущество декларативного подхода через условное форматирование
 */

/**
 * ОБРАЗЕЦ из урока - декларативный подход vs императивный:
 * 
 * Императивный (XML + findViewById):
 * button.setOnClickListener {
 *     nameText.text = "Новое имя"
 *     nameText.setTextColor(Color.RED)
 * }
 * 
 * Декларативный (Compose):
 * @Composable
 * fun GreetingScreen(name: String, isHighlighted: Boolean) {
 *     Text(
 *         text = name,
 *         color = if (isHighlighted) Color.Red else Color.Black
 *     )
 * }
 */

// TODO 1: Создать композабл-функцию, отображающую приветствие с передаваемым именем
// Функция должна принимать параметр name: String

// TODO 2: Добавить к тексту модификаторы (padding, background, размер)
// Используйте цепочку модификаторов: .padding() .background() .padding()
// Пример размера: style = MaterialTheme.typography.headlineSmall

// TODO 3: Реализовать предпросмотр для компонента с разными входными данными 
// Создайте несколько @Preview функций с разными именами

// TODO 4: Продемонстрировать преимущество декларативного подхода через условное форматирование
// Добавьте параметр isHighlighted: Boolean и условную логику цвета/стиля

@Composable
fun Greeting(
    name: String,
    isHighlighted: Boolean = false
) {
    val backgroundColor = if (isHighlighted) {
        Color(0xFFFFF59D)
    } else {
        Color(0xFFE3F2FD)
    }

    val textColor = if (isHighlighted) {
        Color.Red
    } else {
        Color.Black
    }

    Text(
        text = "Hallo, $name!",
        color = textColor,
        style = if (isHighlighted) {
            MaterialTheme.typography.headlineSmall
        } else {
            MaterialTheme.typography.bodyLarge
        },
        modifier = Modifier
            .padding(top = 12.dp)
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    )
}
@Composable
fun DeclarativeComponentsScreen() {
    JetpackComposePlaygroundTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Text(
                    text = "JCP-01: Declarative components",
                    style = MaterialTheme.typography.headlineMedium
                )
                
                // После реализации заданий 1-4, добавьте ваши компоненты здесь:
                // Greeting("Мир") 
                // Greeting("Студент", isHighlighted = true) 
                
                // Пример использования:
                Greeting(name = "World")
                Greeting(name = "Student", isHighlighted = true)
                Greeting(name = "Alex")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeclarativeComponentsScreenPreview() {
    DeclarativeComponentsScreen()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewNormal() {
    JetpackComposePlaygroundTheme {
        Greeting(name = "Мир")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewHighlighted() {
    JetpackComposePlaygroundTheme {
        Greeting(name = "Студент", isHighlighted = true)
    }
}