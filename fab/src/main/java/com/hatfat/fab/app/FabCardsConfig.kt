package com.hatfat.fab.app

import com.hatfat.cards.app.CardsConfig
import javax.inject.Inject

class FabCardsConfig @Inject constructor() : CardsConfig {
    override val shouldDisplayInfoButton: Boolean
        get() = false

    override val shouldDisplayFlipButton: Boolean
        get() = true
}