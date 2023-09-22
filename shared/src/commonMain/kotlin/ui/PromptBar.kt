package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PromptBar(onSend: (String) -> Unit = {}) {
    val value = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    fun sendPrompt() {
        onSend(value.value)
        value.value = ""
        keyboardController?.hide()
    }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value.value,
        onValueChange = { value.value = it },
        label = { Text("New Kurip") },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
        keyboardActions = KeyboardActions(onSend = { sendPrompt() }),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { keyboardController?.hide() }
            )
        }
    )
}
