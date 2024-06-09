package com.hatfat.swccg.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hatfat.cards.data.CardsRepository
import com.hatfat.swccg.data.SWCCGCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("unused")
@Singleton
class SWCCGMetaDataRepository @Inject constructor(
    private val cardRepository: SWCCGCardRepository
) : CardsRepository() {
    private val cardTypesLiveData = MutableLiveData<Set<String>>()
    private val cardSubTypesLiveData = MutableLiveData<Set<String>>()
    private val sidesLiveData = MutableLiveData<Set<String>>()

    val cardTypes: LiveData<Set<String>>
        get() = cardTypesLiveData
    val cardSubTypes: LiveData<Set<String>>
        get() = cardSubTypesLiveData
    val sides: LiveData<Set<String>>
        get() = sidesLiveData

    init {
        cardTypesLiveData.value = HashSet()
        cardSubTypesLiveData.value = HashSet()
        sidesLiveData.value = HashSet()
    }

    override fun setup() {
        cardRepository.sortedCardsArray.observeForever {
            it?.let {
                coroutineScope.launch(coroutineDispatcher) {
                    load(it)
                }
            }
        }
    }

    private suspend fun load(cards: Array<SWCCGCard>) {
        val typesHashSet = HashSet<String>()
        val subtypesHashSet = HashSet<String>()
        val sidesHashSet = HashSet<String>()

        /* populate the metadata sets based on the cards we loaded */
        for (card in cards) {
            if (!card.front.type.isNullOrBlank()) {
                if (card.front.type.contains("#")) {
                    /* trim # from jedi test card types */
                    val fixedType = card.front.type.substring(0, card.front.type.indexOf("#"))
                    typesHashSet.add(fixedType.trim())
                } else {
                    typesHashSet.add(card.front.type)
                }
            }

            if (!card.front.subType.isNullOrBlank()) {
                subtypesHashSet.add(card.front.subType)
            }

            if (!card.side.isNullOrBlank()) {
                sidesHashSet.add(card.side)
            }
        }

        withContext(Dispatchers.Main) {
            cardTypesLiveData.value = typesHashSet
            cardSubTypesLiveData.value = subtypesHashSet
            sidesLiveData.value = sidesHashSet
            loadedLiveData.value = true
        }
    }
}