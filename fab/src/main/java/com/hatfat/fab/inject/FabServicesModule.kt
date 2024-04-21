package com.hatfat.fab.inject

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FabServicesModule {
//    @Provides
//    fun providesGithubPlayersCommitteeService(
//        retrofitFactory: GithubPlayersCommitteeRetrofitFactory
//    ): GithubPlayersCommitteeService {
//        return retrofitFactory.retrofit.create(GithubPlayersCommitteeService::class.java)
//    }
//
//    @Provides
//    fun providesGithubSwccgpcService(
//        retrofitFactory: GithubSwccgpcRetrofitFactory
//    ): GithubSwccgpcService {
//        return retrofitFactory.retrofit.create(GithubSwccgpcService::class.java)
//    }
}