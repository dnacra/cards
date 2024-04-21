package com.hatfat.fab.inject

import com.hatfat.cards.app.CardsConfig
import com.hatfat.cards.data.DataReady
import com.hatfat.cards.results.SearchResultsSerializer
import com.hatfat.cards.results.general.SearchResultsDataProvider
import com.hatfat.cards.search.CardSearchHandler
import com.hatfat.cards.search.CardSearchOptionsProvider
import com.hatfat.fab.app.FabCardsConfig
import com.hatfat.fab.data.FabDataReady
import com.hatfat.fab.results.FabSearchResultsDataProvider
import com.hatfat.fab.results.FabSearchResultsSerializer
import com.hatfat.fab.search.FabCardSearchOptionsProvider
import com.hatfat.fab.search.FabSearchHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class FabModule {

    @Binds
    abstract fun bindDataReady(
        dataReady: FabDataReady
    ): DataReady

    @Binds
    abstract fun bindCardsConfig(
        config: FabCardsConfig
    ): CardsConfig

    @Binds
    abstract fun bindSearchOptionsProvider(
        fabSearchOptionsProvider: FabCardSearchOptionsProvider
    ): CardSearchOptionsProvider

    @Binds
    abstract fun bindSearchHandler(
        fabSearchHandler: FabSearchHandler
    ): CardSearchHandler

    @Binds
    abstract fun bindSearchResultsDataProvider(
        dataProvider: FabSearchResultsDataProvider
    ): SearchResultsDataProvider

    @Binds
    abstract fun bindSearchResultsSerializer(
        serializer: FabSearchResultsSerializer
    ): SearchResultsSerializer
}
