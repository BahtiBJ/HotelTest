package com.bbj.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bbj.database.entities.DBAboutHotel
import com.bbj.database.entities.DBHotel
import com.bbj.database.entities.DBHotelWithRooms
import com.bbj.database.entities.DBRoom
import com.bbj.database.entities.DBTourInfo
import com.bbj.database.entities.ListConverters


const val HOTEL_TABLE = "HOTEL_TABLE"
const val ABOUT_HOTEL_TABLE = "ABOUT_HOTEL_TABLE"
const val ROOM_TABLE = "ROOM_TABLE"
const val TOUR_TABLE = "TOUR_TABLE"

@Database(
    entities = [DBAboutHotel::class, DBHotel::class, DBRoom::class, DBTourInfo::class],
    version = 2
)
@TypeConverters(ListConverters::class)
abstract class TourDatabase : RoomDatabase() {

    abstract fun getTourDAO() : TourDAO

}