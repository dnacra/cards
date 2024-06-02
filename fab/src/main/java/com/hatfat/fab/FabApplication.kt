package com.hatfat.fab

import com.hatfat.cards.app.CardsApplication
import com.hatfat.fab.repo.FabCardRepository
import com.hatfat.fab.repo.FabSetRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class FabApplication : CardsApplication() {
    @Inject
    lateinit var cardRepository: FabCardRepository

    //    @Inject
//    lateinit var formatRepository: SWCCGFormatRepository
//
    @Inject
    lateinit var setRepository: FabSetRepository

//    @Inject
//    lateinit var metaDataRepository: SWCCGMetaDataRepository
//
//    @Inject
//    lateinit var searchResultsRepository: SearchResultsRepository
//
    override fun onCreate() {
        super.onCreate()

        cardRepository.prepare()
//        formatRepository.prepare()
        setRepository.prepare()
//        metaDataRepository.prepare()
//        searchResultsRepository.prepare()
    }
}
