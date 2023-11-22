package com.bbj.data.models


data class DataRoom(
    val id: Int,
    val imageU1rls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    val pricePer: String
)