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
            Text(text = stringState.value)
        }
    }
}
