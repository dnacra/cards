package com.hatfat.fab.search.filter.set

import com.hatfat.cards.search.filter.SpinnerOption

data class FabSetOption(
    val name: String,
    val id: String
) : SpinnerOption {
    override val displayName: String
        get() = name
}