package com.bbj.network.models

import com.google.gson.annotations.SerializedName

data class ResponseRoom(
    @SerializedName("about_the_hotel")
    val id: Int,

    @SerializedName("image_urls")
    val imageU1rls: List<String>,

    @SerializedName("name")
    val name: String,

    @SerializedName("peculiarities")
    val peculiarities: List<String>,

    @SerializedName("price")
    val price: Int,

    @SerializedName("price_per")
    val pricePer: String
)