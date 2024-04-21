package com.hatfat.swccg.search.filter.advanced

import com.hatfat.cards.search.filter.advanced.AdvancedFilter
import com.hatfat.cards.search.filter.advanced.AdvancedFilterMode
import com.hatfat.fab.data.FabCard
import com.hatfat.fab.search.filter.FabFilter
import com.hatfat.fab.search.filter.advanced.FabAdvancedFilterField
import java.io.Serializable

class FabAdvancedFilter(
    override val fields: List<FabAdvancedFilterField>,
    override val modes: List<AdvancedFilterMode>
) : AdvancedFilter(
    fields,
    modes
), FabFilter, Serializable {
    override fun filter(card: FabCard, /* setRepository: SWCCGSetRepository */): Boolean {
        return true
//        val field = fields[selectedFieldIndex]
//        val fieldValues = field.getFieldValuesForCard(card, setRepository)
//
//        if (fieldValues.isEmpty()) {
//            return false
//        }
//
//        return fieldsFilter(fieldValues)
    }
}