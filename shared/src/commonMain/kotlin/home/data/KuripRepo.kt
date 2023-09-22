package home.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.KuripModel

class KuripRepo(
    inMemorySource: InMemorySource = InMemorySource()
) {
    private val _kuripFlow = MutableStateFlow(emptyList<KuripModel>())

    fun getKurips(): StateFlow<List<KuripModel>> {
        return _kuripFlow.asStateFlow()
    }

    fun addKurip(kurip: KuripModel) {
        _kuripFlow.update { it + kurip }
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
