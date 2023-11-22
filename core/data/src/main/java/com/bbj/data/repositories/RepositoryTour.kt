package com.bbj.data.repositories

import com.bbj.data.models.DataTourInfo
import com.bbj.data.models.RequestTour

interface RepositoryTour {

    suspend fun requestTourInfo(requestTour: RequestTour = RequestTour()) : DataTourInfo

}