package ru.ievetrov.jetpackcomposeplayground.tasks.jcp02

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

/**
 * JCP-02: Переключаемые карточки
 *
 * Задание:
 * 1. Реализовать список карточек с возможностью разворачивания/сворачивания
 * 2. Показывать дополнительную информацию в развёрнутом состоянии
 * 3. Анимировать переход между состояниями
 * 4. Обеспечить, чтобы одновременно была развернута только одна карточка
 */

data class CardItem(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val fullDescription: String
)

val sampleCards = listOf(
    CardItem(
        id = 1,
        title = "Jetpack Compose",
        shortDescription = "Современный UI toolkit для Android",
        fullDescription = "Jetpack Compose — это декларативный toolkit для создания нативного интерфейса Android. Он упрощает разработку UI и делает код более читаемым и гибким."
    ),
    CardItem(
        id = 2,
        title = "State в Compose",
        shortDescription = "Состояние управляет интерфейсом",
        fullDescription = "Состояние в Compose определяет, что именно отображается на экране. Когда состояние меняется, Compose автоматически перерисовывает нужные части интерфейса."
    ),
    CardItem(
        id = 3,
        title = "LazyColumn",
        shortDescription = "Эффективный список элементов",
        fullDescription = "LazyColumn отображает только видимые элементы списка, поэтому хорошо подходит для длинных списков. Это аналог RecyclerView в мире Compose."
    )
)

@Composable
fun ExpandableCard(
    card: CardItem,
    isExpanded: Boolean,
    onCardClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .clickable { onCardClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = card.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = card.shortDescription,
                style = MaterialTheme.typography.bodyMedium
            )

            AnimatedVisibility(visible = isExpanded) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = card.fullDescription,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
fun ExpandableCardsScreen() {
    var expandedCardId by remember { mutableIntStateOf(-1) }

    JetpackComposePlaygroundTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Text(
                    text = "JCP-02: Переключаемые карточки",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(sampleCards) { card ->
                        ExpandableCard(
                            card = card,
                            isExpanded = expandedCardId == card.id,
                            onCardClick = {
                                expandedCardId =
                                    if (expandedCardId == card.id) {
                                        -1
                                    } else {
                                        card.id
                                    }
                            }
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpandableCardsScreenPreview() {
    ExpandableCardsScreen()
}