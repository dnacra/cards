package com.hatfat.fab.data

import java.io.Serializable

data class FabCardIdList(
    val cardIds: List<String>,
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FabCardIdList

        return cardIds.equals(other.cardIds)
    }

    override fun hashCode(): Int {
        return cardIds.hashCode()
    }
}
