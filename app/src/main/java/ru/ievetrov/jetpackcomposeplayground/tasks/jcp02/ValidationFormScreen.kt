package ru.ievetrov.jetpackcomposeplayground.tasks.jcp02

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

/**
 * JCP-02: Форма ввода с валидацией
 *
 * Задание:
 * 1. Реализовать форму с полями: имя, email, пароль
 * 2. Добавить валидацию:
 *    - Имя: не пустое, минимум 2 символа
 *    - Email: соответствие формату email
 *    - Пароль: минимум 8 символов, наличие цифры и спецсимвола
 * 3. Отображать сообщения об ошибках под полями
 * 4. Активировать кнопку отправки только при валидных данных
 * 5. Показывать сообщение об успехе при успешной отправке
 */

fun validateName(name: String): Boolean {
    return name.trim().length >= 2
}

fun validateEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun validatePassword(password: String): Boolean {
    val hasDigit = password.any { it.isDigit() }
    val hasSpecialChar = password.any { !it.isLetterOrDigit() }
    return password.length >= 8 && hasDigit && hasSpecialChar
}

@Composable
fun ValidationFormScreen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showSuccess by remember { mutableStateOf(false) }

    val nameError = when {
        name.isEmpty() -> "Имя не должно быть пустым"
        !validateName(name) -> "Имя должно содержать минимум 2 символа"
        else -> null
    }

    val emailError = when {
        email.isEmpty() -> "Email не должен быть пустым"
        !validateEmail(email) -> "Введите корректный email"
        else -> null
    }

    val passwordError = when {
        password.isEmpty() -> "Пароль не должен быть пустым"
        !validatePassword(password) -> "Пароль: минимум 8 символов, 1 цифра и 1 спецсимвол"
        else -> null
    }

    val isFormValid = nameError == null && emailError == null && passwordError == null

    JetpackComposePlaygroundTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Text(
                    text = "JCP-02: Форма с валидацией",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        showSuccess = false
                    },
                    label = { Text("Имя") },
                    isError = nameError != null
                )

                if (nameError != null) {
                    Text(
                        text = nameError,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        showSuccess = false
                    },
                    label = { Text("Email") },
                    isError = emailError != null,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                if (emailError != null) {
                    Text(
                        text = emailError,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        showSuccess = false
                    },
                    label = { Text("Пароль") },
                    isError = passwordError != null,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                if (passwordError != null) {
                    Text(
                        text = passwordError,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        showSuccess = true
                    },
                    enabled = isFormValid
                ) {
                    Text("Отправить")
                }

                if (showSuccess) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Форма успешно отправлена!",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ValidationFormScreenPreview() {
    ValidationFormScreen()
}