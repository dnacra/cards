package com.hatfat.fab.search.filter

import com.hatfat.fab.search.FabSearchResult

interface FabFilter {
    fun filter(searchResult: FabSearchResult): FabSearchResult?
}