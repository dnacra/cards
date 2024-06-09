package com.hatfat.fab.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hatfat.cards.data.CardsRepository
import com.hatfat.fab.data.FabCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("unused")
@Singleton
class FabMetaDataRepository @Inject constructor(
    private val cardRepository: FabCardRepository
) : CardsRepository() {
//    Legality
//    All cards
//    blitz
//    classic constructed
//    Commoner
    private val cardTypesLiveData = MutableLiveData<Set<String>>()
    private val cardKeywordsLiveData = MutableLiveData<Set<String>>()

    val types: LiveData<Set<String>>
        get() = cardTypesLiveData
    val keywords: LiveData<Set<String>>
        get() = cardKeywordsLiveData

    init {
        cardTypesLiveData.value = HashSet()
        cardKeywordsLiveData.value = HashSet()
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

    private suspend fun load(cards: Array<FabCard>) {
        val typesHashSet = HashSet<String>()
        val keywordsHashSet = HashSet<String>()

        /* populate the metadata sets based on the cards we loaded */
        for (card in cards) {
            typesHashSet.addAll(card.types)
            keywordsHashSet.addAll(card.card_keywords)
            keywordsHashSet.addAll(card.granted_keywords)
            keywordsHashSet.addAll(card.removed_keywords)
            keywordsHashSet.addAll(card.interacts_with_keywords)
            keywordsHashSet.addAll(card.ability_and_effect_keywords)
        }

        /* Process keywords for 'Ward #' and similar elements */
        val numRegex = Regex(" (\\d+|X)$")

        val keywordsToRemove = HashSet<String>()
        val keywordsToAdd = HashSet<String>()

        for (keyword in keywordsHashSet) {
            numRegex.find(keyword)?.let {
                keywordsToRemove.add(keyword)
                keywordsToAdd.add(keyword.removeRange(it.range))
            }
        }

        keywordsHashSet.removeAll(keywordsToRemove)
        keywordsHashSet.addAll(keywordsToAdd)

        withContext(Dispatchers.Main) {
            cardTypesLiveData.value = typesHashSet
            cardKeywordsLiveData.value = keywordsHashSet
            loadedLiveData.value = true
        }
    }
}
