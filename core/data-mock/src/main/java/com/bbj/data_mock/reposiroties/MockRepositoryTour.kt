package com.bbj.data_mock.reposiroties

import com.bbj.data.models.DataTourInfo
import com.bbj.data.models.RequestTour
import com.bbj.data.repositories.RepositoryTour
import com.bbj.data_mock.models.FromDatabaseToDataMapper
import com.bbj.data_mock.models.FromNetworkToDataMapper
import com.bbj.data_mock.models.ToDatabaseMapper
import com.bbj.database.TourDAO
import com.bbj.network.NetworkApi

class MockRepositoryTour(
    private val networkApi: NetworkApi,
    private val tourDAO: TourDAO
) : RepositoryTour {

    private val mockTourId = 1

    override suspend fun requestTourInfo(requestTour: RequestTour): DataTourInfo {
        val tourFromDB = tourDAO.getTourById(mockTourId)
        return if (tourFromDB == null) {
            val tourFromNetwork = FromNetworkToDataMapper.map(networkApi.getTourInfo())
            tourDAO.insertTour(ToDatabaseMapper.map(tourFromNetwork))
            tourFromNetwork
        } else {
            FromDatabaseToDataMapper.map(tourFromDB)
        }
    }
}