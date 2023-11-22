package com.bbj.data_mock.reposiroties

import com.bbj.data.models.DataRoomList
import com.bbj.data.models.RequestRoom
import com.bbj.data.repositories.RepositoryRoom
import com.bbj.data_mock.models.FromDatabaseToDataMapper
import com.bbj.data_mock.models.FromNetworkToDataMapper
import com.bbj.data_mock.models.ToDatabaseMapper
import com.bbj.database.TourDAO
import com.bbj.network.NetworkApi

class MockRepositoryRoom(
    private val networkApi: NetworkApi,
    private val tourDAO: TourDAO
) : RepositoryRoom {

    private val mockHotelId = 1

    override suspend fun requestRooms(requestRoom: RequestRoom): DataRoomList {
        val roomsFromDB = tourDAO.getRoomsById(mockHotelId)
        return if (roomsFromDB.isEmpty()) {
            val roomsFromNetwork = FromNetworkToDataMapper.map(networkApi.getRooms())
            tourDAO.insertRooms(ToDatabaseMapper.map(roomsFromNetwork,mockHotelId.toLong()))
            roomsFromNetwork
        } else {
           FromDatabaseToDataMapper.map(roomsFromDB)
        }
    }
}