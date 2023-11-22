package com.bbj.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.bbj.database.entities.DBHotel
import com.bbj.database.entities.DBHotelWithRooms
import com.bbj.database.entities.DBRoom
import com.bbj.database.entities.DBTourInfo

@Dao
interface TourDAO {

    @Transaction
    @Query("SELECT * FROM HOTEL_TABLE WHERE id = :hotelId")
    fun getHotelsWithRooms(hotelId : Int) : DBHotelWithRooms

    @Query("SELECT * FROM TOUR_TABLE WHERE id = :id")
    fun getTourById(id:Int) : DBTourInfo?

    @Query("SELECT * FROM HOTEL_TABLE WHERE id = :id")
    fun getHotelById(id:Int) : DBHotel?

    @Query("SELECT * FROM ROOM_TABLE WHERE hotelId = :hotelId")
    fun getRoomsById(hotelId: Int) : List<DBRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHotel(dbHotel: DBHotel) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRooms(dbRooms: List<DBRoom>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTour(dbTourInfo: DBTourInfo)

    @Delete
    fun deleteHotel(dbHotel: DBHotel)

    @Delete
    fun deleteRoom(dbRooms: DBRoom)

    @Delete
    fun deleteTour(dbTourInfo: DBTourInfo)
}