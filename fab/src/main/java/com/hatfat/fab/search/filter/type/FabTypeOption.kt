package com.hatfat.fab.search.filter.type

import com.hatfat.cards.search.filter.SpinnerOption

data class FabTypeOption(
    val type: String,
) : SpinnerOption {
    override val displayName: String
        get() = type
}