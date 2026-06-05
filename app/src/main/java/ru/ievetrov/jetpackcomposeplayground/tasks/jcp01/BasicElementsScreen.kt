package ru.ievetrov.jetpackcomposeplayground.tasks.jcp01

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight


/**
 * JCP-01: Базовые UI-элементы
 *
 * Задание:
 * 1. Реализовать экран с элементами: Text, Button, Image
 * 2. Продемонстрировать варианты кнопок: Button, OutlinedButton, TextButton
 * 3. Создать пример с Image и различными contentScale
 * 4. Добавить TextField для ввода текста
 * 5. Реализовать Card с составным содержимым
 */

@Composable
fun BasicElementsScreen() {
    Surface(
        modifier = Modifier.padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "JCP-01: Базовые UI-элементы",
                style = MaterialTheme.typography.headlineMedium
            )

            /**
             * ПРИМЕРЫ из урока - базовые компоненты:
             *
             * // Текст
             * Text(
             *     text = "Заголовок",
             *     style = MaterialTheme.typography.headlineSmall
             * )
             *
             * // Кнопка
             * Button(onClick = { /* действие */ }) {
             *     Text("Нажми меня")
             * }
             *
             * // Поле ввода
             * var text by remember { mutableStateOf("") }
             * TextField(
             *     value = text,
             *     onValueChange = { text = it },
             *     label = { Text("Введите текст") }
             * )
             */

            // TODO 1: Реализовать экран с элементами: Text, Button, Image
            // Создайте различные Text с разными style

            // TODO 2: Продемонстрировать варианты кнопок: Button, OutlinedButton, TextButton
            // Покажите различия в стилях кнопок

            // TODO 3: Создать пример с Image и различными contentScale
            // Пример: Image(painterResource(R.drawable.sample), contentScale = ContentScale.Crop)
            // Попробуйте: ContentScale.Fit, ContentScale.FillWidth, ContentScale.Inside

            // TODO 4: Добавить TextField для ввода текста
            // Используйте: var text by remember { mutableStateOf("") }

            // TODO 5: Реализовать Card с составным содержимым
            // Card { Column { Text() + Button() + Image() } }

            var text by remember { mutableStateOf("") }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Пример обычного текста",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Заголовок",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { }) {
                Text("Button")
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(onClick = { }) {
                Text("OutlinedButton")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = { }) {
                Text("TextButton")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Image + contentScale",
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(android.R.drawable.ic_menu_gallery),
                    contentDescription = "Crop image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray)
                )

                Image(
                    painter = painterResource(android.R.drawable.ic_menu_gallery),
                    contentDescription = "Fit image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray)
                )

                Image(
                    painter = painterResource(android.R.drawable.ic_menu_gallery),
                    contentDescription = "Inside image",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Введите текст") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (text.isBlank()) {
                    "Пока ничего не введено"
                } else {
                    "Вы ввели: $text"
                },
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Card с составным содержимым",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Image(
                        painter = painterResource(android.R.drawable.ic_menu_gallery),
                        contentDescription = "Card image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.LightGray),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = "Внутри Card можно комбинировать текст, изображение и кнопки."
                    )

                    Button(onClick = { }) {
                        Text("Подробнее")
                    }
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun BasicElementsScreenPreview() {
    JetpackComposePlaygroundTheme {
        BasicElementsScreen()
    }
}