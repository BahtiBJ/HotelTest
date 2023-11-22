package com.bbj.hoteltest.di

import androidx.room.Room
import com.bbj.database.TourDAO
import com.bbj.database.TourDatabase
import com.bbj.database.entities.ListConverters
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single<TourDatabase>{
        Room.databaseBuilder(
            androidContext(),
            TourDatabase::class.java, "database-tour"
        )
            .build()
    }

    single<TourDAO>{
        val tourDatabase : TourDatabase = get()
        tourDatabase.getTourDAO()
    }

}