package home.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.KuripModel

class KuripRepo(
    private val inMemorySource: InMemorySource = InMemorySource()
) {
    private val _kuripFlow = MutableStateFlow(emptyList<KuripModel>())
    val kuripFlow = _kuripFlow.asStateFlow()

    suspend fun refreshKurips() {
        _kuripFlow.value = inMemorySource.getKurips().sortedByDescending { it.lastModified }
    }

    suspend fun addKurip(kurip: KuripModel) {
        inMemorySource.addKurip(kurip)
        refreshKurips()
    }
}

class InMemorySource : KuripSource {
    private val kurips = mutableListOf<KuripModel>()

    override suspend fun getKurips(): List<KuripModel> {
        return kurips
    }

    override suspend fun addKurip(kurip: KuripModel) {
        kurips.add(kurip)
    }
}

interface KuripSource {
    suspend fun getKurips(): List<KuripModel>
    suspend fun addKurip(kurip: KuripModel)
}
