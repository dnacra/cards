package com.hatfat.fab.service

import com.hatfat.fab.data.FabCard
import retrofit2.http.GET

interface GithubFabService {
    @GET("card.json")
    suspend fun getCardJson(): List<FabCard>
}