package com.hatfat.fab.search

import com.hatfat.cards.data.SearchResults
import com.hatfat.cards.search.CardSearchHandler
import com.hatfat.cards.search.filter.SearchParams
import com.hatfat.fab.data.FabCardIdList
import javax.inject.Inject

class FabSearchHandler @Inject constructor(
//    private val cardRepo: SWCCGCardRepository,
//    private val setRepository: SWCCGSetRepository
) : CardSearchHandler {
    override fun performSearch(searchParams: SearchParams): SearchResults {
//        val filters = mutableListOf<FabFilter>()
//
//        if (searchParams.textFilters.isNotEmpty()) {
//            val searchOptions = searchParams.textFilters.map { it.extra as SWCCGTextFilterMode }.toSet()
//            val stringFilter = SWCCGTextFilter(searchParams.basicTextSearchString, searchOptions)
//            filters.add(stringFilter)
//        }
//
//        if (searchParams.spinnerFilters.isNotEmpty()) {
//            val spinnerFilters = searchParams.spinnerFilters.map { it as SWCCGFilter }
//            filters.addAll(spinnerFilters)
//        }
//
//        if (searchParams.advancedfilters.isNotEmpty()) {
//            val advancedFilters = searchParams.advancedfilters.map { it as SWCCGFilter }
//            filters.addAll(advancedFilters)
//        }
//
//        var results = cardRepo.sortedCardIds.value?.cardIds?.toList() ?: emptyList()
//
//        filters.forEach { filter ->
//            results = results.filter { cardId ->
//                cardRepo.cardsMap.value?.get(cardId)?.let { card ->
//                    filter.filter(card, setRepository)
//                } ?: false
//            }
//        }
//
//        return FabSearchResults(SWCCGCardIdList(results.toIntArray()))
        return FabSearchResults(FabCardIdList(IntArray(0)))
    }
}