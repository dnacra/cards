package com.hatfat.fab.inject

import com.hatfat.fab.service.GithubFabRetrofitFactory
import com.hatfat.fab.service.GithubFabService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FabServicesModule {
    @Provides
    fun providesGithubFabService(
        retrofitFactory: GithubFabRetrofitFactory
    ): GithubFabService {
        return retrofitFactory.retrofit.create(GithubFabService::class.java)
    }
}