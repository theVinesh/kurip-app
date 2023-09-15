import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.launch

val emitter = Emitter()

@Composable
fun App() {
    MaterialTheme {
        LaunchedEffect("") {
            launch { emitter.start() }
        }
        val stringState = emitter.flow.collectAsState()

        Scaffold {
            Card {
                Column {
                    Text(text = stringState.value)
                    Button(onClick = { }) {
                        Text(text = "Click me")
                    }
                }
            }

        }
    }
}
