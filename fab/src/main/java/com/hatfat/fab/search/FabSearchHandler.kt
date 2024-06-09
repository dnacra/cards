package com.hatfat.fab.search

import com.hatfat.cards.data.SearchResults
import com.hatfat.cards.search.CardSearchHandler
import com.hatfat.cards.search.filter.SearchParams
import com.hatfat.fab.repo.FabCardRepository
import com.hatfat.fab.search.filter.FabFilter
import com.hatfat.fab.search.filter.text.FabTextFilter
import com.hatfat.fab.search.filter.text.FabTextFilterMode
import javax.inject.Inject

class FabSearchHandler @Inject constructor(
    private val cardRepo: FabCardRepository,
) : CardSearchHandler {
    override fun performSearch(searchParams: SearchParams): SearchResults {
        val filters = mutableListOf<FabFilter>()

        if (searchParams.textFilters.isNotEmpty()) {
            val searchOptions =
                searchParams.textFilters.map { it.extra as FabTextFilterMode }.toSet()
            val stringFilter =
                FabTextFilter(searchParams.basicTextSearchString, searchOptions)
            filters.add(stringFilter)
        }

        if (searchParams.spinnerFilters.isNotEmpty()) {
            val spinnerFilters = searchParams.spinnerFilters.map { it as FabFilter }
            filters.addAll(spinnerFilters)
        }

        if (searchParams.advancedfilters.isNotEmpty()) {
            val advancedFilters = searchParams.advancedfilters.map { it as FabFilter }
            filters.addAll(advancedFilters)
        }

        var results = cardRepo.sortedSearchResults.value ?: emptyList()

        filters.forEach { filter ->
            results = results.mapNotNull { searchResult ->
                filter.filter(searchResult, cardRepo)
            }
        }

        return FabSearchResults(results)
    }
}