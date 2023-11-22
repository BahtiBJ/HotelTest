package com.bbj.data_mock.models

import com.bbj.data.models.DataAboutHotel
import com.bbj.data.models.DataHotel
import com.bbj.data.models.DataRoom
import com.bbj.data.models.DataRoomList
import com.bbj.data.models.DataTourInfo
import com.bbj.database.entities.DBAboutHotel
import com.bbj.database.entities.DBHotel
import com.bbj.database.entities.DBRoom
import com.bbj.database.entities.DBTourInfo

internal object FromDatabaseToDataMapper {

    fun map(dbHotel: DBHotel): DataHotel {
        return dbHotel.run {
            DataHotel(
                map(aboutHotel),
                adress, id, imageUrls, minimalPrice, name, priceForIt, rating, ratingName
            )
        }
    }

    private fun map(dbAboutHotel: DBAboutHotel): DataAboutHotel {
        return dbAboutHotel.run {
            DataAboutHotel(
                description, peculiarities
            )
        }
    }

    fun map(dbTourInfo: DBTourInfo): DataTourInfo {
        return dbTourInfo.run {
            DataTourInfo(
                arrivalCountry,
                departure,
                fuelCharge,
                horating,
                hotelAddress,
                hotelName,
                id,
                numberOfNights,
                nutrition,
                ratingName,
                room,
                serviceCharge,
                tourDateStart,
                tourDateStop,
                tourPrice
            )
        }
    }

    private fun map(dbRoom: DBRoom): DataRoom {
        return dbRoom.run {
            DataRoom(
                id, imageU1rls, name, peculiarities, price, pricePer
            )
        }
    }

    fun map(dbRooms: List<DBRoom>) : DataRoomList{
        val resultList = arrayListOf<DataRoom>()
        dbRooms.forEach {
            resultList.add(map(it))
        }
        return DataRoomList(resultList)
    }

}