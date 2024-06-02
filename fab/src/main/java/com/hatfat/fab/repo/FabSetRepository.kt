package com.hatfat.fab.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.reflect.TypeToken
import com.hatfat.cards.data.CardsRepository
import com.hatfat.cards.data.loader.DataDesc
import com.hatfat.cards.data.loader.DataLoader
import com.hatfat.fab.R
import com.hatfat.fab.data.FabSet
import com.hatfat.fab.service.GithubFabService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FabSetRepository @Inject constructor(
    private val fabService: GithubFabService,
    private val dataLoader: DataLoader,
) : CardsRepository() {
    /* set ID --> Set */
    private val setMapLiveData = MutableLiveData<Map<String, FabSet>>()
    val setMap: LiveData<Map<String,FabSet>>
        get() = setMapLiveData

    private val setListLiveData = MutableLiveData<List<FabSet>>()
    val setList: LiveData<List<FabSet>>
        get() = setListLiveData

    init {
        setMapLiveData.value = HashMap()
        setListLiveData.value = mutableListOf()
    }

    override suspend fun load() {
        val typeToken = object : TypeToken<List<FabSet>>() {}
        val dataDesc = DataDesc(
            typeToken,
            { fabService.getSetJson() },
            R.raw.set,
            emptyList(),
            "sets",
        )

        val sets = dataLoader.load(dataDesc).sortedBy { it.name }

        val hashMap = HashMap<String, FabSet>()
        for (set in sets) {
            hashMap[set.id] = set
        }

        withContext(Dispatchers.Main) {
            setMapLiveData.value = hashMap
            setListLiveData.value = sets
            loadedLiveData.value = true
        }
    }
}
