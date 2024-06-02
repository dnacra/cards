package com.hatfat.fab.search.filter.set

import com.hatfat.cards.search.filter.SpinnerFilter
import com.hatfat.fab.repo.FabCardRepository
import com.hatfat.fab.search.FabSearchResult
import com.hatfat.fab.search.filter.FabFilter
import java.io.Serializable

class FabSetFilter(
    options: List<FabSetOption>,
    notSelectedOption: FabSetOption?,
    private val cardRepository: FabCardRepository,
) : SpinnerFilter(
    options,
    notSelectedOption
), FabFilter, Serializable {
    override fun filter(searchResult: FabSearchResult): FabSearchResult? {
        cardRepository.cardsMap.value?.get(searchResult.cardId)?.let { card ->
            val printingResults = searchResult.printingIds.filter { printingId ->
                val printing = card.printings.find { printingId == it.unique_id }

                if (printing != null) {
                    printing.set_id == (selectedOption as FabSetOption).id
                } else {
                    // Shouldn't happen; should always find printing
                    assert(false)
                    false
                }
            }

            if (printingResults.isNotEmpty()) {
                return FabSearchResult(searchResult.cardId, printingResults)
            }
        }

        return null
    }
}