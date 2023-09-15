import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainVm(
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    private val _completionsFlow = MutableStateFlow("")
    val completionsFlow = _completionsFlow.asStateFlow()

    private val vmScope = CoroutineScope(coroutineDispatcher)

    fun sendPrompt(prompt: String) {
        println("Sending prompt: $prompt")
        vmScope.launch {
            OpenAiUtil.getCompletion(prompt).collect {
                _completionsFlow.emit(completionsFlow.value + it)
            }
        }
    }
}
