import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.OpenAIConfig
import com.thevinesh.kurip.shared.BuildKonfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

internal enum class ChatGptModels(val modelId: String) {
    GPT_3_5_TURBO("gpt-3.5-turbo"),
}

internal object OpenAiUtil {
    private val config = OpenAIConfig(
        token = BuildKonfig.OPENAI_KEY,
    )
    private val openAI = OpenAI(config)

    fun getCompletion(
        prompt: String,
        model: ChatGptModels = ChatGptModels.GPT_3_5_TURBO
    ): Flow<String> = try {
        /*openAI.chatCompletions(
            ChatCompletionRequest(
                messages = listOf(ChatMessage(role = ChatRole.User, content = prompt)),
                model = ModelId(model.modelId)
            )
        ).map { it.choices[0].delta.content ?: "" }*/
        flow {
            prompt.toCharArray().forEach {
                emit(it.toString())
                delay(500)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        flowOf("Error getting completion: ${e.message}")
    }
}
