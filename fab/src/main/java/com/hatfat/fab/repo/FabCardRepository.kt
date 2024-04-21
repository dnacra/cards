package com.hatfat.fab.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.reflect.TypeToken
import com.hatfat.cards.data.CardsRepository
import com.hatfat.cards.data.loader.DataDesc
import com.hatfat.cards.data.loader.DataLoader
import com.hatfat.fab.R
import com.hatfat.fab.data.FabCard
import com.hatfat.fab.data.FabCardIdList
import com.hatfat.fab.service.GithubFabService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FabCardRepository @Inject constructor(
    private val fabService: GithubFabService,
    private val dataLoader: DataLoader,
) : CardsRepository() {
    private val cardHashMapLiveData = MutableLiveData<Map<String, FabCard>>()
    private val sortedCardArrayLiveData = MutableLiveData<Array<FabCard>>()
    private val sortedCardIdsListLiveData = MutableLiveData<FabCardIdList>()

    val cardsMap: LiveData<Map<String, FabCard>>
        get() = cardHashMapLiveData

    val sortedCardsArray: LiveData<Array<FabCard>>
        get() = sortedCardArrayLiveData

    val sortedCardIds: LiveData<FabCardIdList>
        get() = sortedCardIdsListLiveData

    init {
        cardHashMapLiveData.value = HashMap()
    }

    override suspend fun load() {
        val typeToken = object : TypeToken<List<FabCard>>() {}

        val cardDataDesc = DataDesc(
            typeToken,
            { fabService.getCardJson() },
            R.raw.card,
            emptyList(),
            "card",
        )

        val results = mutableListOf<List<FabCard>>()
        val hashMap = HashMap<String, FabCard>()

        val cardLoadingTasks = listOf(
            coroutineScope.async(coroutineDispatcher) {
                results.add(dataLoader.load(cardDataDesc))
            },
        )

        /* wait for all cards to load */
        cardLoadingTasks.awaitAll()

        results.forEach { cardList ->
            cardList.forEach { card ->
                hashMap[card.unique_id] = card
            }
        }

        Log.i(TAG, "Loaded ${hashMap.values.size} cards total.")

        val array = hashMap.values.toTypedArray()
        array.sort()

        withContext(Dispatchers.Main) {
            cardHashMapLiveData.value = hashMap
            sortedCardArrayLiveData.value = array
            sortedCardIdsListLiveData.value = FabCardIdList(array.map { it.unique_id })
            loadedLiveData.value = true
        }
    }

    companion object {
        private val TAG = FabCardRepository::class.java.simpleName
    }
}
