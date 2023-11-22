package com.bbj.hoteltest.di

import com.bbj.hoteltest.ui.booking.ViewModelBooking
import com.bbj.hoteltest.ui.hotel.ViewModelHotel
import com.bbj.hoteltest.ui.rooms.ViewModelRooms
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        ViewModelHotel(get())
    }

    viewModel {
        ViewModelRooms(get())
    }

    viewModel {
        ViewModelBooking(get())
    }
}