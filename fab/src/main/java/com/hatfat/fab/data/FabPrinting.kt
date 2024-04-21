package com.hatfat.fab.data

data class FabPrinting(
    val unique_id: String,
    val set_printing_unique_id: String,
    val id: String,
    val set_id: String,
    val edition: String,
    val foiling: String,
    val rarity: String,
    val artist: String,
    val art_variation: String?,
    val flavor_text: String,
    val flavor_text_plain: String,
    val image_url: String,
    val tcgplayer_product_id: String,
    val tcgplayer_url: String,
)