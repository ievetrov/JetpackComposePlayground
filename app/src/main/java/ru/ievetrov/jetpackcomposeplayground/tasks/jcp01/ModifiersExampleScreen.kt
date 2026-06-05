package ru.ievetrov.jetpackcomposeplayground.tasks.jcp01

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.CircleShape
/**
 * JCP-01: Работа с модификаторами
 *
 * Задание:
 * 1. Реализовать примеры основных модификаторов (size, padding, background)
 * 2. Продемонстрировать цепочку модификаторов и порядок их применения
 * 3. Создать примеры с clip, border и shape
 * 4. Добавить интерактивный модификатор clickable
 * 5. Показать разницу между внешним и внутренним padding
 */

@Composable
fun ModifiersExampleScreen() {

        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "JCP-01: Работа с модификаторами",
                    style = MaterialTheme.typography.headlineMedium
                )

                /**
                 * ПРИМЕР из урока - цепочка модификаторов:
                 *
                 * Text(
                 *     text = "Стильный текст",
                 *     modifier = Modifier
                 *         .padding(16.dp)           // Внешние отступы
                 *         .background(Color.Gray)   // Фон
                 *         .padding(8.dp)           // Внутренние отступы
                 *         .fillMaxWidth()          // На всю ширину
                 * )
                 */

                // TODO 1: Реализовать примеры основных модификаторов (size, padding, background)
                // Пример: .size(100.dp), .padding(16.dp), .background(Color.Blue)

                // TODO 2: Продемонстрировать цепочку модификаторов и порядок их применения
                // Покажите разницу: .padding().background() vs .background().padding()

                // TODO 3: Создать примеры с clip, border и shape
                // Пример: .clip(RoundedCornerShape(8.dp)), .border(2.dp, Color.Red)

                // TODO 4: Добавить интерактивный модификатор clickable
                // Пример: .clickable { /* действие при нажатии */ }

                // TODO 5: Показать разницу между внешним и внутренним padding
                // Подсказка: сравните .padding().background() и .background().padding()

                var clickCount by remember { mutableStateOf(0) }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "1. size + padding + background",
                    style = MaterialTheme.typography.titleMedium
                )

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Blue)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Box", color = Color.White)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "2. Reihenfolge der Modifier",
                    style = MaterialTheme.typography.titleMedium
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "padding → background",
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Color.LightGray)
                            .padding(8.dp)
                    )

                    Text(
                        text = "background → padding",
                        modifier = Modifier
                            .background(Color.Yellow)
                            .padding(16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "3. clip + border + shape",
                    style = MaterialTheme.typography.titleMedium
                )

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Cyan)
                        .border(3.dp, Color.Red, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Shape")
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Zusätzlich: CircleShape",
                    style = MaterialTheme.typography.titleMedium
                )

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.Magenta)
                        .border(2.dp, Color.Black, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("OK", color = Color.White)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "4. clickable",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Klick mich! Anzahl Klicks: $clickCount",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFE3F2FD))
                        .clickable { clickCount++ }
                        .padding(16.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "5. Außen- vs Innen-Padding",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Außen-Padding",
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.Green)
                        .padding(8.dp)
                )

                Text(
                    text = "Innen-Padding",
                    modifier = Modifier
                        .background(Color.Green)
                        .padding(16.dp)
                )

            }
        }

}

@Preview(showBackground = true)
@Composable
fun ModifiersExampleScreenPreview() {
    JetpackComposePlaygroundTheme {
        ModifiersExampleScreen()
    }
}