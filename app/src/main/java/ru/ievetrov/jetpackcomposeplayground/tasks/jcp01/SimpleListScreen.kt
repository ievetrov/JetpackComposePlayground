package ru.ievetrov.jetpackcomposeplayground.tasks.jcp01

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

data class ListItem(
    val id: Int,
    val title: String,
    val subtitle: String
)

data class CategoryItem(
    val id: Int,
    val name: String,
    val color: Color
)

sealed interface FeedItem {
    data class Header(val text: String) : FeedItem
    data class Info(val text: String) : FeedItem
    data class Content(val item: ListItem) : FeedItem
    data class Footer(val text: String) : FeedItem
}

@Composable
fun SimpleListScreen() {
    val categories = remember {
        listOf(
            CategoryItem(1, "Compose", Color(0xFFE3F2FD)),
            CategoryItem(2, "UI", Color(0xFFFFF9C4)),
            CategoryItem(3, "State", Color(0xFFE8F5E9)),
            CategoryItem(4, "Lists", Color(0xFFF3E5F5)),
            CategoryItem(5, "Modifiers", Color(0xFFFFEBEE)),
            CategoryItem(6, "Material3", Color(0xFFE0F7FA))
        )
    }

    val listItems = remember {
        List(50) { index ->
            ListItem(
                id = index,
                title = "Элемент #$index",
                subtitle = "Описание элемента списка №$index"
            )
        }
    }

    val feedItems = remember(listItems) {
        buildList {
            add(FeedItem.Header("Разные типы элементов в одном списке"))
            add(FeedItem.Info("LazyColumn показывает только видимые элементы, поэтому список работает эффективно даже при большом количестве данных."))
            listItems.forEach { add(FeedItem.Content(it)) }
            add(FeedItem.Footer("Конец списка"))
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "JCP-01: Простые списки",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Горизонтальный список: LazyRow",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                items(categories, key = { it.id }) { category ->
                    SuggestionChip(
                        onClick = { },
                        label = { Text(category.name) },
                        modifier = Modifier.background(
                            color = category.color,
                            shape = RoundedCornerShape(20.dp)
                        )
                    )
                }
            }

            Text(
                text = "Вертикальный список: LazyColumn",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 12.dp)
            ) {
                items(
                    items = feedItems,
                    key = {
                        when (it) {
                            is FeedItem.Header -> "header"
                            is FeedItem.Info -> "info"
                            is FeedItem.Content -> it.item.id
                            is FeedItem.Footer -> "footer"
                        }
                    }
                ) { feedItem ->
                    when (feedItem) {
                        is FeedItem.Header -> {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(18.dp)
                            ) {
                                Text(
                                    text = feedItem.text,
                                    modifier = Modifier.padding(16.dp),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }

                        is FeedItem.Info -> {
                            ElevatedCard(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(18.dp)
                            ) {
                                Text(
                                    text = feedItem.text,
                                    modifier = Modifier.padding(16.dp),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }

                        is FeedItem.Content -> {
                            ListItemCard(item = feedItem.item)
                        }

                        is FeedItem.Footer -> {
                            Text(
                                text = feedItem.text,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ListItemCard(item: ListItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.id.toString(),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = item.subtitle,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SimpleListScreenPreview() {
    JetpackComposePlaygroundTheme {
        SimpleListScreen()
    }
}