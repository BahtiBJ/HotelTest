package com.bbj.database.entities

import androidx.room.PrimaryKey

data class DBRoomList(
    val rooms: List<DBRoom>,
    @PrimaryKey(autoGenerate = true) val primaryId : Int = 0
)