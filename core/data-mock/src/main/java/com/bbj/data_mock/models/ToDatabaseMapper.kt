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

object ToDatabaseMapper {


    fun map(dataHotel: DataHotel): DBHotel {
        return dataHotel.run {
            DBHotel(
                map(aboutHotel),
                adress, id, imageUrls, minimalPrice, name, priceForIt, rating, ratingName
            )
        }
    }

    private fun map(dataAboutHotel: DataAboutHotel): DBAboutHotel {
        return dataAboutHotel.run {
            DBAboutHotel(
                description, peculiarities
            )
        }
    }

    fun map(dataTourInfo: DataTourInfo): DBTourInfo {
        return dataTourInfo.run {
            DBTourInfo(
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

    private fun map(dataRoom: DataRoom,hotelId: Long): DBRoom {
        return dataRoom.run {
            DBRoom(
                id, imageU1rls, name, peculiarities, price, pricePer, hotelId = hotelId
            )
        }
    }

    fun map(dataRoomList: DataRoomList,hotelId: Long) : List<DBRoom> {
        val resultList = arrayListOf<DBRoom>()
        dataRoomList.rooms.forEach {
            resultList.add(map(it,hotelId))
        }
        return resultList
    }


}