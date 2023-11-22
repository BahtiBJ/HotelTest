package com.bbj.data.repositories

import com.bbj.data.models.DataRoomList
import com.bbj.data.models.RequestRoom

interface RepositoryRoom {

    suspend fun requestRooms(requestRoom: RequestRoom = RequestRoom()) : DataRoomList

}