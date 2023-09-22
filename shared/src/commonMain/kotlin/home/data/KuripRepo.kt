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
    private val kurips = mutableListOf<KuripModel>(
        KuripModel(
            id = 0,
            body = "Hello World"
        ),
        KuripModel(
            id = 1,
            body = "Lorem Impsum Dolor Sit Amet Consectetur Adipiscing Elit Sed Do Eiusmod Tempor Incididunt Ut Labore Et Dolore Magna Aliqua Ut Enim Ad Minim Veniam Quis Nostrud Exercitation Ullamco Laboris Nisi Ut Aliquip Ex Ea Commodo Consequat Duis Aute Irure Dolor In Reprehenderit In Voluptate Velit Esse Cillum Dolore Eu Fugiat Nulla Pariatur Excepteur Sint Occaecat Cupidatat Non Proident Sunt In Culpa Qui Officia Deserunt Mollit Anim Id Est Laborum"
        ),
        KuripModel(
            id = 2,
            body = "Hello World"
        ),
        KuripModel(
            id = 3,
            body = "Lorem Impsum \nDolor \nSit Amet\n Consectetur \nAdipiscing Elit\n Sed Do \n\n\n\nEiusmod Tempor \nIncididunt Ut Labore Et Dolore Magna Aliqua Ut Enim Ad Minim Veniam Quis Nostrud Exercitation Ullamco Laboris Nisi Ut Aliquip Ex Ea\n Commodo Consequat Duis Aute Irure Dolor In Reprehenderit In Voluptate Velit Esse Cillum Dolore Eu Fugiat Nulla Pariatur Excepteur Sint Occaecat Cupidatat Non\n Proident Sunt In Culpa Qui Officia Deserunt \nMollit Anim Id Est Laborum"
        ),
        KuripModel(
            id = 4,
            body = "Hello World"
        ),
        KuripModel(
            id = 5,
            body = "Hello World"
        ),
        KuripModel(
            id = 6,
            body = "Hello World"
        ),
    )

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
