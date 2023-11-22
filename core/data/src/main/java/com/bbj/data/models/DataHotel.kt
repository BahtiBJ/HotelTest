package com.bbj.data.models


data class DataHotel(
    val aboutHotel: DataAboutHotel,
    val adress: String,
    val id: Int,
    val imageUrls: List<String>,
    val minimalPrice: Int,
    val name: String,
    val priceForIt: String,
    val rating: Int,
    val ratingName: String
)