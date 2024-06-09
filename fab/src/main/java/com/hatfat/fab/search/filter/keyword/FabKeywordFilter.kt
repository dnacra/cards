package com.hatfat.fab.search.filter.keyword

import com.hatfat.cards.search.filter.SpinnerFilter
import com.hatfat.fab.repo.FabCardRepository
import com.hatfat.fab.search.FabSearchResult
import com.hatfat.fab.search.filter.FabFilter
import java.io.Serializable

class FabKeywordFilter(
    options: List<FabKeywordOption>,
    notSelectedOption: FabKeywordOption,
) : SpinnerFilter(
    options,
    notSelectedOption
), FabFilter, Serializable {
    override fun filter(
        searchResult: FabSearchResult,
        cardRepository: FabCardRepository
    ): FabSearchResult? {
        cardRepository.cardsMap.value?.get(searchResult.cardId)?.let { card ->
            if (
                card.card_keywords.any {
                    it.startsWith(selectedOption.displayName, true)
                }
                ||
                card.granted_keywords.any {
                    it.startsWith(selectedOption.displayName, true)
                }
                ||
                card.removed_keywords.any {
                    it.startsWith(selectedOption.displayName, true)
                }
                ||
                card.interacts_with_keywords.any {
                    it.startsWith(selectedOption.displayName, true)
                }
                ||
                card.ability_and_effect_keywords.any {
                    it.startsWith(selectedOption.displayName, true)
                }
            ) {
                return searchResult
            } else {
                return null
            }
        }

        return null
    }
}