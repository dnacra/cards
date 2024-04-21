package com.hatfat.fab.results

import android.content.Context
import com.hatfat.cards.data.SearchResults
import com.hatfat.cards.glide.CardZoomTransformation
import com.hatfat.cards.results.general.SearchResultsCardData
import com.hatfat.cards.results.general.SearchResultsDataProvider
import com.hatfat.fab.R
import com.hatfat.fab.repo.FabCardRepository
import com.hatfat.fab.search.FabSearchResults
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FabSearchResultsDataProvider @Inject constructor(
    private val cardRepository: FabCardRepository,
//    private val setRepository: SWCCGSetRepository,
    @ApplicationContext private val context: Context,
) : SearchResultsDataProvider() {

    //    private val cardBackHelper = SWCCGCardBackHelper()
//
    private val standardZoomTransformation =
        CardZoomTransformation(0.333f, 1.0625f, 1.125f, 0.9375f)
//    private val noLoreZoomTransformation = CardZoomTransformation(0.375f, 0.625f)
//    private val admiralsOrderZoomTransformation = CardZoomTransformation(0.25f, 0.75f)
//    private val locationTransformation = CardZoomTransformation(0.25f, 1.125f, 0.75f, 0.6875f)

    override fun getCardDataForPosition(
        searchResults: SearchResults, position: Int, cardData: SearchResultsCardData
    ) {
        (searchResults as FabSearchResults).also {
            val cardId = it.getResult(position)
            cardRepository.cardsMap.value?.get(cardId)?.let { card ->
//                val set = setRepository.setMap.value?.get(card.set)?.name
//                    ?: context.getString(R.string.unknown)
//                val rarity = card.rarity ?: context.getString(R.string.unknown)
//                val side = card.side ?: context.getString(R.string.unknown)

                cardData.title = card.name
                cardData.subtitle = "TODO"
//                cardData.listExtraText = setRepository.setMap.value?.get(card.set)?.abbr
                cardData.listExtraText = "TODO2"
                cardData.carouselInfoText1 = "TODO3"
                cardData.carouselInfoText2 = "TODO4"
                cardData.carouselInfoText3 = "TODO5"
                cardData.frontImageUrl = card.printings[0].image_url
                cardData.cardBackResourceId = R.drawable.fab_card_back
                cardData.cardZoomTransformation = standardZoomTransformation
            }
        }
    }
}