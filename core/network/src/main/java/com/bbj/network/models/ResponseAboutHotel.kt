package com.bbj.network.models

import com.google.gson.annotations.SerializedName

data class ResponseAboutHotel(
    @SerializedName("description")
    val description: String,

    @SerializedName("peculiarities")
    val peculiarities: List<String>
)