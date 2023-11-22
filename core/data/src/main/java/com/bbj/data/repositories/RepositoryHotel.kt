package com.bbj.data.repositories

import com.bbj.data.models.DataHotel
import com.bbj.data.models.RequestHotel

interface RepositoryHotel {

    suspend fun requestHotelInfo(requestHotel: RequestHotel = RequestHotel()) : DataHotel

}