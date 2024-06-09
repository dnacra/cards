package com.hatfat.fab.search.filter.text

import com.hatfat.fab.repo.FabCardRepository
import com.hatfat.fab.search.FabSearchResult
import com.hatfat.fab.search.filter.FabFilter

class FabTextFilter(
    private val filterText: String,
    private val textFilterModes: Set<FabTextFilterMode>,
) : FabFilter {
    override fun filter(
        searchResult: FabSearchResult,
        cardRepository: FabCardRepository
    ): FabSearchResult? {
        cardRepository.cardsMap.value?.get(searchResult.cardId)?.let { card ->
            if (textFilterModes.contains(FabTextFilterMode.NAME) && card.name.contains(
                    filterText,
                    true
                )
            ) {
                return searchResult
            }

            if (textFilterModes.contains(FabTextFilterMode.GAMETEXT) &&
                card.functional_text_plain.contains(filterText, true)
            ) {
                return searchResult
            }
        }

        return null
    }
}