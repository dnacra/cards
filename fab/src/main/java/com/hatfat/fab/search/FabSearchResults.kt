package com.hatfat.fab.search

import com.hatfat.cards.data.SearchResults
import com.hatfat.fab.data.FabCardIdList

class FabSearchResults(
    private val fabCardIdList: FabCardIdList
) : SearchResults() {
    override val size: Int
        get() = fabCardIdList.cardIds.size

    override fun getResult(position: Int): String {
        return fabCardIdList.cardIds[position]
    }
}