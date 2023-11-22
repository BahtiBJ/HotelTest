package com.bbj.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.bbj.database.ABOUT_HOTEL_TABLE


@Entity(tableName = ABOUT_HOTEL_TABLE)
data class DBAboutHotel(
    val description: String,
    val peculiarities: List<String>,
    @PrimaryKey(autoGenerate = true) val primaryId : Int = 0
)