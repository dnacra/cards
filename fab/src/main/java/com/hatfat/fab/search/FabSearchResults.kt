package com.hatfat.fab.search

import com.hatfat.cards.data.SearchResults

class FabSearchResults(
    private val results: List<FabSearchResult>
) : SearchResults() {
    override val size: Int
        get() = results.size

    override fun getResult(position: Int): FabSearchResult {
        return results[position]
    }
}