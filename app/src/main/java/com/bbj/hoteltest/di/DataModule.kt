package com.bbj.hoteltest.di

import com.bbj.data.repositories.RepositoryHotel
import com.bbj.data.repositories.RepositoryRoom
import com.bbj.data.repositories.RepositoryTour
import com.bbj.data_mock.reposiroties.MockRepositoryHotel
import com.bbj.data_mock.reposiroties.MockRepositoryRoom
import com.bbj.data_mock.reposiroties.MockRepositoryTour
import org.koin.dsl.module

val dataModule = module {

    single<RepositoryHotel> {
        MockRepositoryHotel(get(),get())
    }

    single<RepositoryRoom> {
        MockRepositoryRoom(get(),get())
    }

    single<RepositoryTour> {
        MockRepositoryTour(get(),get())
    }

}