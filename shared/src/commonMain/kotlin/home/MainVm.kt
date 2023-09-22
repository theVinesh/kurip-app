package home

import home.data.KuripRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.KuripModel

class MainVm(
    private val kuripRepo: KuripRepo = KuripRepo(),
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    val kuripFlow = kuripRepo.kuripFlow

    private val vmScope = CoroutineScope(coroutineDispatcher)

    init {
        vmScope.launch {
            kuripRepo.refreshKurips()
        }
    }

    fun addKurip(text: String) {
        vmScope.launch {
            kuripRepo.addKurip(
                kurip = KuripModel(
                    id = kuripFlow.value.size.toLong(),
                    body = text,
                )
            )
        }
    }
}
