package com.bbj.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bbj.database.ROOM_TABLE


@Entity(tableName = ROOM_TABLE)
data class DBRoom(
    val id: Int,
    val imageU1rls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    val pricePer: String,
    val hotelId : Long,
    @PrimaryKey(autoGenerate = true) val primaryRoomId : Int = 0
)