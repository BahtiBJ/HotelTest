package com.bbj.database.entities

import androidx.room.Embedded
import androidx.room.Relation

class DBHotelWithRooms (
    @Embedded val hotel : DBHotel,
    @Relation(
        parentColumn = "primaryHotelId",
        entityColumn =  "hotelId"
    ) val rooms : List<DBRoom>
)