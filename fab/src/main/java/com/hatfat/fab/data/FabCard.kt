@file:Suppress("PropertyName")

package com.hatfat.fab.data

import java.io.Serializable

data class FabCard(
    val unique_id: String,
    val name: String,
    val pitch: String,
    val cost: String,
    val power: String,
    val defense: String,
    val health: String,
    val intelligence: String,
    val types: List<String>,
    val card_keywords: List<String>,
    val abilities_and_effects: List<String>,
    val ability_and_effect_keywords: List<String>,
    val granted_keywords: List<String>,
    val removed_keywords: List<String>,
    val interacts_with_keywords: List<String>,
    val functional_text: String,
    val functional_text_plain: String,
//"type_text": "Wizard Defense Reaction",
//"played_horizontally": false,
//"blitz_legal": true,
//"cc_legal": true,
//"commoner_legal": false,
//"blitz_living_legend": false,
//"cc_living_legend": false,
//"blitz_banned": false,
//"cc_banned": false,
//"commoner_banned": false,
//"upf_banned": false,
//"blitz_suspended": false,
//"cc_suspended": false,
//"commoner_suspended": false,
//"ll_restricted": false,
    val printings: List<FabPrinting>,
) : Serializable, Comparable<FabCard> {

    override fun compareTo(other: FabCard): Int {
        return name.compareTo(other.name)
    }
}
