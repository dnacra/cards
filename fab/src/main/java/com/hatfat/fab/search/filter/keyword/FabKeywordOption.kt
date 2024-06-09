package com.hatfat.fab.search.filter.keyword

import com.hatfat.cards.search.filter.SpinnerOption

data class FabKeywordOption(
    val keyword: String,
) : SpinnerOption {
    override val displayName: String
        get() = keyword
}