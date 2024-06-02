package com.hatfat.fab.search

data class FabSearchResult(
    val cardId: String,
    val printingIds: List<String>,
    var currentPrintingIndex: Int = 0,
)