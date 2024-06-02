package com.hatfat.fab.service

import com.hatfat.fab.data.FabCard
import com.hatfat.fab.data.FabSet
import retrofit2.http.GET

interface GithubFabService {
    @GET("card.json")
    suspend fun getCardJson(): List<FabCard>

    @GET("set.json")
    suspend fun getSetJson(): List<FabSet>
}