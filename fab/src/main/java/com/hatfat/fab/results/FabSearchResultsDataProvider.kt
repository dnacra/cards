package com.hatfat.fab.results

import android.content.Context
import com.hatfat.cards.data.SearchResults
import com.hatfat.cards.glide.CardZoomTransformation
import com.hatfat.cards.results.general.SearchResultsCardData
import com.hatfat.cards.results.general.SearchResultsDataProvider
import com.hatfat.fab.R
import com.hatfat.fab.repo.FabCardRepository
import com.hatfat.fab.repo.FabSetRepository
import com.hatfat.fab.search.FabSearchResults
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FabSearchResultsDataProvider @Inject constructor(
    private val cardRepository: FabCardRepository,
    private val setRepository: FabSetRepository,
    @ApplicationContext private val context: Context,
) : SearchResultsDataProvider() {

    private val standardZoomTransformation =
        CardZoomTransformation(0.25f, 0.6f, 1.125f, 0.9375f)

    override fun getCardDataForPosition(
        searchResults: SearchResults, position: Int, cardData: SearchResultsCardData
    ) {
        (searchResults as FabSearchResults).also {
            val result = it.getResult(position)
            cardRepository.cardsMap.value?.get(result.cardId)?.let { card ->


                val printing = card.getPrinting(result.printingIds[result.currentPrintingIndex])
                val set = setRepository.setMap.value?.get(printing?.set_id)
                val setName = set?.name ?: context.getString(R.string.unknown)
                val setAbbr = set?.id ?: ""
                val rarity = printing?.rarity ?: context.getString(R.string.unknown)
                val types = card.types.joinToString(", ")

                cardData.title = card.name
                cardData.subtitle = card.type_text
                cardData.listExtraText = setAbbr
                cardData.carouselInfoText1 = types
                cardData.carouselInfoText2 = rarity
                cardData.carouselInfoText3 = setName
                cardData.frontImageUrl = printing?.image_url
                cardData.cardBackResourceId = R.drawable.fab_card_back
                cardData.cardZoomTransformation = standardZoomTransformation
            }
        }
    }
}