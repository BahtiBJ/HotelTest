package com.bbj.data_mock.models

import com.bbj.data.models.DataAboutHotel
import com.bbj.data.models.DataHotel
import com.bbj.data.models.DataRoom
import com.bbj.data.models.DataRoomList
import com.bbj.data.models.DataTourInfo
import com.bbj.network.models.ResponseAboutHotel
import com.bbj.network.models.ResponseHotel
import com.bbj.network.models.ResponseRoom
import com.bbj.network.models.ResponseRoomList
import com.bbj.network.models.ResponseTourInfo

internal object FromNetworkToDataMapper {

    fun map(responseHotel: ResponseHotel): DataHotel {
        return responseHotel.run {
            DataHotel(
                map(aboutHotel),
                adress, id, imageUrls, minimalPrice, name, priceForIt, rating, ratingName
            )
        }
    }

    private fun map(responseAboutHotel: ResponseAboutHotel): DataAboutHotel {
        return responseAboutHotel.run {
            DataAboutHotel(
                description, peculiarities
            )
        }
    }

    fun map(responseTourInfo: ResponseTourInfo): DataTourInfo {
        return responseTourInfo.run {
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

    private fun map(responseRoom: ResponseRoom): DataRoom {
        return responseRoom.run {
            DataRoom(
                id, imageU1rls, name, peculiarities, price, pricePer
            )
        }
    }

    fun map(responseRoom: ResponseRoomList) : DataRoomList{
        val resultList = arrayListOf<DataRoom>()
        responseRoom.rooms.forEach {
            resultList.add(map(it))
        }
        return DataRoomList(resultList)
    }

}