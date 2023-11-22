package com.bbj.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bbj.database.HOTEL_TABLE

@Entity(tableName = HOTEL_TABLE)
data class DBHotel(
    @Embedded val aboutHotel: DBAboutHotel,
    val adress: String,
    val id: Int,
    val imageUrls: List<String>,
    val minimalPrice: Int,
    val name: String,
    val priceForIt: String,
    val rating: Int,
    val ratingName: String,
    @PrimaryKey(autoGenerate = true) val primaryHotelId : Int = 0
)