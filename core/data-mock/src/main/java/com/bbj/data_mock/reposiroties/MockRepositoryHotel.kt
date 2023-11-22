package com.bbj.data_mock.reposiroties

import com.bbj.data.models.DataHotel
import com.bbj.data.models.RequestHotel
import com.bbj.data.repositories.RepositoryHotel
import com.bbj.data_mock.models.FromDatabaseToDataMapper
import com.bbj.data_mock.models.FromNetworkToDataMapper
import com.bbj.data_mock.models.ToDatabaseMapper
import com.bbj.database.TourDAO
import com.bbj.network.NetworkApi

class MockRepositoryHotel(
    private val networkApi: NetworkApi,
    private val tourDAO: TourDAO
) : RepositoryHotel {

    private val mockHotelId = 1

    override suspend fun requestHotelInfo(requestHotel: RequestHotel): DataHotel {
        val hotelFromDB = tourDAO.getHotelById(mockHotelId)
        return if (hotelFromDB == null) {
            val hotelFromNetwork = FromNetworkToDataMapper.map(networkApi.getHotelInfo())
            tourDAO.insertHotel(ToDatabaseMapper.map(hotelFromNetwork))
            hotelFromNetwork
        } else {
            FromDatabaseToDataMapper.map(hotelFromDB)
        }
    }
}