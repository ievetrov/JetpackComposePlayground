package ru.ievetrov.jetpackcomposeplayground.tasks.jcp04

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.ievetrov.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

@Composable
fun StateFlowScreen(
    stateHolder: FormStateHolder = viewModel()
) {
    JetpackComposePlaygroundTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            val formState by stateHolder.formState.collectAsState()
            val validationState by stateHolder.validationState.collectAsState()

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "JCP-04: Работа с StateFlow",
                    style = MaterialTheme.typography.headlineMedium
                )

                OutlinedTextField(
                    value = formState.name,
                    onValueChange = { stateHolder.updateName(it) },
                    label = { Text("Имя") },
                    isError = formState.name.isNotEmpty() && !validationState.isNameValid,
                    modifier = Modifier.fillMaxWidth()
                )

                ValidationMessage(
                    isVisible = formState.name.isNotEmpty() && !validationState.isNameValid,
                    text = "Имя должно содержать минимум 2 символа"
                )

                OutlinedTextField(
                    value = formState.email,
                    onValueChange = { stateHolder.updateEmail(it) },
                    label = { Text("Email") },
                    isError = formState.email.isNotEmpty() && !validationState.isEmailValid,
                    modifier = Modifier.fillMaxWidth()
                )

                ValidationMessage(
                    isVisible = formState.email.isNotEmpty() && !validationState.isEmailValid,
                    text = "Email должен содержать @ и точку"
                )

                OutlinedTextField(
                    value = formState.age,
                    onValueChange = { stateHolder.updateAge(it) },
                    label = { Text("Возраст") },
                    isError = formState.age.isNotEmpty() && !validationState.isAgeValid,
                    modifier = Modifier.fillMaxWidth()
                )

                ValidationMessage(
                    isVisible = formState.age.isNotEmpty() && !validationState.isAgeValid,
                    text = "Возраст должен быть числом от 18 до 100"
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Форма валидна: ${if (validationState.isFormValid) "да" else "нет"}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Button(
                    enabled = validationState.isFormValid,
                    onClick = { stateHolder.submitForm() }
                ) {
                    Text("Отправить")
                }

                if (formState.submittedMessage.isNotBlank()) {
                    Text(
                        text = formState.submittedMessage,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun ValidationMessage(
    isVisible: Boolean,
    text: String
) {
    if (isVisible) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

data class FormState(
    val name: String = "",
    val email: String = "",
    val age: String = "",
    val submittedMessage: String = ""
)

data class ValidationState(
    val isNameValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isAgeValid: Boolean = false
) {
    val isFormValid: Boolean
        get() = isNameValid && isEmailValid && isAgeValid
}

class FormStateHolder : ViewModel() {
    private val _formState = MutableStateFlow(FormState())
    val formState: StateFlow<FormState> = _formState.asStateFlow()

    val validationState: StateFlow<ValidationState> = formState
        .map { form ->
            ValidationState(
                isNameValid = form.name.trim().length >= 2,
                isEmailValid = form.email.contains("@") && form.email.contains("."),
                isAgeValid = form.age.toIntOrNull()?.let { it in 18..100 } ?: false
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ValidationState()
        )

    fun updateName(name: String) {
        _formState.update {
            it.copy(
                name = name,
                submittedMessage = ""
            )
        }
    }

    fun updateEmail(email: String) {
        _formState.update {
            it.copy(
                email = email,
                submittedMessage = ""
            )
        }
    }

    fun updateAge(age: String) {
        _formState.update {
            it.copy(
                age = age.filter(Char::isDigit),
                submittedMessage = ""
            )
        }
    }

    fun submitForm() {
        _formState.update { form ->
            form.copy(
                submittedMessage = "Форма отправлена: ${form.name}, ${form.email}, ${form.age}"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StateFlowScreenPreview() {
    StateFlowScreen(stateHolder = FormStateHolder())
}