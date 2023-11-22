package com.bbj.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bbj.database.ABOUT_HOTEL_TABLE
import com.bbj.database.TOUR_TABLE

@Entity(tableName = TOUR_TABLE)
data class DBTourInfo(
    val arrivalCountry: String,
    val departure: String,
    val fuelCharge: Int,
    val horating: Int,
    val hotelAddress: String,
    val hotelName: String,
    val id: Int,
    val numberOfNights: Int,
    val nutrition: String,
    val ratingName: String,
    val room: String,
    val serviceCharge: Int,
    val tourDateStart: String,
    val tourDateStop: String,
    val tourPrice: Int,
    @PrimaryKey(autoGenerate = true) val primaryTourId : Int = 0
)