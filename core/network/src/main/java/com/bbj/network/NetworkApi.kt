package com.bbj.network

import com.bbj.network.models.ResponseHotel
import com.bbj.network.models.ResponseRoomList
import com.bbj.network.models.ResponseTourInfo
import retrofit2.http.GET

interface NetworkApi {

    @GET("d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotelInfo() : ResponseHotel

    @GET("8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getRooms() : ResponseRoomList

    @GET("63866c74-d593-432c-af8e-f279d1a8d2ff")
    suspend fun getTourInfo() : ResponseTourInfo

}