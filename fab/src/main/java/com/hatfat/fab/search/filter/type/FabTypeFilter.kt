package com.hatfat.fab.search.filter.type

import com.hatfat.cards.search.filter.SpinnerFilter
import com.hatfat.fab.repo.FabCardRepository
import com.hatfat.fab.search.FabSearchResult
import com.hatfat.fab.search.filter.FabFilter
import java.io.Serializable

class FabTypeFilter(
    options: List<FabTypeOption>,
    notSelectedOption: FabTypeOption,
) : SpinnerFilter(
    options,
    notSelectedOption
), FabFilter, Serializable {
    override fun filter(
        searchResult: FabSearchResult,
        cardRepository: FabCardRepository
    ): FabSearchResult? {
        cardRepository.cardsMap.value?.get(searchResult.cardId)?.let { card ->
            if (card.types.contains(selectedOption.displayName)) {
                return searchResult
            } else {
                return null
            }
        }

        return null
    }
}