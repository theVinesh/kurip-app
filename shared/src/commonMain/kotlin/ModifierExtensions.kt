import androidx.compose.ui.Modifier

fun Modifier.ifTrue(
    condition: Boolean,
    block: Modifier.() -> Modifier,
): Modifier = if (condition) block(this) else this

fun Modifier.ifFalse(
    condition: Boolean,
    block: Modifier.() -> Modifier,
): Modifier = if (condition) this else block(this)
