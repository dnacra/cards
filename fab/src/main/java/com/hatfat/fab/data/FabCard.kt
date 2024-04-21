package com.hatfat.fab.data

import java.io.Serializable

data class FabCard(
    val test: String,
//    val abbr: List<String>?,
//    val back: SWCCGCardFace?,
//    val canceledBy: List<String>?,
//    val cancels: List<String>?,
//    val combo: List<String>?,
//    val conceptBy: String?,
//    val counterpart: String?,
//    val front: SWCCGCardFace,
//    val gempId: String?,
//    val id: Int?,
//    val legacy: Boolean?,
//    val matching: List<String>?,
//    val matchingWeapon: List<String>?,
//    var printings: Set<SWCCGPrinting>?,
//    val pulledBy: List<String>?,
//    val pulls: List<String>?,
//    val rarity: String?,
//    val rulings: List<String>?,
//    var set: String?,
//    val side: String?,
//    val sourceType: String?,
) : Serializable, Comparable<FabCard> {

    override fun compareTo(other: FabCard): Int {
        return test.compareTo(other.test)
    }
}
