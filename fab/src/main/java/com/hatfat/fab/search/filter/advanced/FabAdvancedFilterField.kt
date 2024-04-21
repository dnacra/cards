package com.hatfat.fab.search.filter.advanced

import com.hatfat.cards.search.filter.advanced.AdvancedFilterField
import com.hatfat.fab.data.FabCard
import java.io.Serializable

data class FabAdvancedFilterField(
    val fabField: FabField
) : AdvancedFilterField, Serializable {

    override val displayName: String
        get() = fabField.displayName

    fun getFieldValuesForCard(card: FabCard /* setRepository: SWCCGSetRepository */): List<String> {
        return mutableListOf<String>().apply {
//            when (swccgField) {
//                FabField.GAMETEXT -> {
//                    card.front.gametext?.let { this.add(it) }
//                    card.back?.gametext?.let { this.add(it) }
//                }
//                FabField.FORFEIT -> {
//                    card.front.forfeit?.let { this.add(it) }
//                    card.back?.forfeit?.let { this.add(it) }
//                }
//                FabField.ABILITY -> {
//                    card.front.ability?.let { this.add(it) }
//                    card.back?.ability?.let { this.add(it) }
//                }
//                FabField.ARMOR -> {
//                    card.front.armor?.let { this.add(it) }
//                    card.back?.armor?.let { this.add(it) }
//                }
//                FabField.CHARACTERISTIC -> {
//                    card.front.characteristics?.forEach { this.add(it) }
//                    card.back?.characteristics?.forEach { this.add(it) }
//                }
//                FabField.CONCEPT_BY -> {
//                    card.conceptBy?.let { this.add(it) }
//                }
//                FabField.DARK_SIDE_ICONS -> {
//                    card.front.darkSideIcons?.let { this.add(it.toString()) }
//                    card.back?.darkSideIcons?.let { this.add(it.toString()) }
//                }
//                FabField.DEPLOY -> {
//                    card.front.deploy?.let { this.add(it) }
//                    card.back?.deploy?.let { this.add(it) }
//                }
//                FabField.DESTINY -> {
//                    card.front.destiny?.let { this.add(it) }
//                    card.front.destinyValues?.forEach { this.add(it.toString()) }
//                    card.back?.destiny?.let { this.add(it) }
//                    card.back?.destinyValues?.forEach { this.add(it.toString()) }
//                }
//                FabField.EXTRA_TEXT -> {
//                    card.front.extraText?.forEach { this.add(it) }
//                    card.back?.extraText?.forEach { this.add(it) }
//                }
//                FabField.HYPERSPEED -> {
//                    card.front.hyperspeed?.let { this.add(it) }
//                    card.back?.hyperspeed?.let { this.add(it) }
//                }
//                FabField.ICONS -> {
//                    card.front.icons?.forEach { this.add(it) }
//                    card.back?.icons?.forEach { this.add(it) }
//                }
//                FabField.LANDSPEED -> {
//                    card.front.landspeed?.let { this.add(it) }
//                    card.back?.landspeed?.let { this.add(it) }
//                }
//                FabField.LIGHT_SIDE_ICONS -> {
//                    card.front.lightSideIcons?.let { this.add(it.toString()) }
//                    card.back?.lightSideIcons?.let { this.add(it.toString()) }
//                }
//                FabField.LORE -> {
//                    card.front.lore?.let { this.add(it) }
//                    card.back?.lore?.let { this.add(it) }
//                }
//                FabField.MANEUVER -> {
//                    card.front.maneuver?.let { this.add(it) }
//                    card.back?.maneuver?.let { this.add(it) }
//                }
//                FabField.PARSEC -> {
//                    card.front.parsec?.let { this.add(it) }
//                    card.back?.parsec?.let { this.add(it) }
//                }
//                FabField.POLITICS -> {
//                    card.front.politics?.let { this.add(it) }
//                    card.back?.politics?.let { this.add(it) }
//                }
//                FabField.POWER -> {
//                    card.front.power?.let { this.add(it) }
//                    card.back?.power?.let { this.add(it) }
//                }
//                FabField.RARITY -> {
//                    card.rarity?.let { this.add(it) }
//                }
//                FabField.SET -> {
//                    card.set?.let { setKey ->
//                        this.add(setKey)
//
//                        val set = setRepository.setMap.value?.get(setKey)
//                        set?.let {
//                            this.add(it.name)
//                        }
//                    }
//                }
//                FabField.SIDE -> {
//                    card.side?.let { this.add(it) }
//                }
//                FabField.SUB_TYPE -> {
//                    card.front.subType?.let { this.add(it) }
//                    card.back?.subType?.let { this.add(it) }
//                }
//                FabField.TITLE -> {
//                    card.front.title?.let { this.add(it) }
//                    card.back?.title?.let { this.add(it) }
//                }
//                FabField.TYPE -> {
//                    card.front.type?.let { this.add(it) }
//                    card.back?.type?.let { this.add(it) }
//                }
//                FabField.UNIQUENESS -> {
//                    card.front.uniqueness?.let { this.add(it) }
//                    card.back?.uniqueness?.let { this.add(it) }
//                }
//                FabField.PRINTINGS -> {
//                    card.printings?.forEach { printing ->
//                        printing.set?.let { setKey ->
//                            this.add(setKey)
//
//                            val set = setRepository.setMap.value?.get(setKey)
//                            set?.let {
//                                this.add(it.name)
//                            }
//                        }
//                    }
//                }
//                FabField.FEROCITY -> {
//                    card.front.ferocity?.let { this.add(it) }
//                    card.back?.ferocity?.let { this.add(it) }
//                }
//            }
        }
    }
}