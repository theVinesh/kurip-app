import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.yield

class Emitter {
    companion object {
        private val strings = listOf(
            "Hello",
            "World",
            "From",
            "Kotlin",
        )
    }

    private var index = 0
    private val _state = MutableStateFlow(strings[index])
    val flow = _state.asStateFlow()
    suspend fun start() {
        println("Started")
        while (true) {
            yield()
            delay(1000L)
            if (index == strings.size) {
                index = 0
            }
            println(index)
            _state.emit(strings[index++])
        }
    }
}
