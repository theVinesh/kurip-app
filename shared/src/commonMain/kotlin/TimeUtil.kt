import kotlinx.datetime.Clock

object TimeUtil {
    fun now(): Long {
        return Clock.System.now().epochSeconds
    }
}
