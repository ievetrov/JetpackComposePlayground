package ru.ievetrov.jetpackcomposeplayground.tasks.myjcp1


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme

@Composable
fun MyGreeting(
    name: String,
    isHighlighted: Boolean = false,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hallo, $name!",
        color = if (isHighlighted) Color.Red else Color.Green,
        style = if (isHighlighted) {
            MaterialTheme.typography.headlineSmall
        } else {
            MaterialTheme.typography.bodyLarge
        },
        modifier = Modifier
            .background(if (isHighlighted) Color.Yellow else Color.LightGray)
            .padding(16.dp)
    )
}


@Composable
fun myScreen() {
    JetpackComposePlaygroundTheme {

        Column (modifier = Modifier.padding(16.dp)) {
            Text("Welcome Damen und Herren!!!",
                style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(12.dp))
           
            MyGreeting("Rudolf", true)
            MyGreeting("Samson")
            MyGreeting("Olga")
        }
        //Greeting("Ivan", true)
    }
}


@Preview(showBackground = true)
@Composable
fun MyGreetingPreviewNormal() {
    JetpackComposePlaygroundTheme{
        MyGreeting("Stepan")
    }
}

@Preview(showBackground = true)
@Composable
fun MyGreetingPreviewHighlighted() {
    // Greeting("Stepan", false)
    MyGreeting("Ivan", true)
}

@Preview(showBackground = true)
@Composable
fun MyGreetingPreview() {
    // Greeting("Stepan", false)
    myScreen()
}
