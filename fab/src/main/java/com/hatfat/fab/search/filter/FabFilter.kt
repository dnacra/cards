package com.hatfat.fab.search.filter

import com.hatfat.fab.data.FabCard

interface FabFilter {
    fun filter(card: FabCard /* setRepository: SWCCGSetRepository */): Boolean
}