package com.bbj.hoteltest.ui.booking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbj.data.models.DataHotel
import com.bbj.data.models.DataTourInfo
import com.bbj.data.models.StateModel
import com.bbj.data.repositories.RepositoryHotel
import com.bbj.data.repositories.RepositoryTour
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelBooking(
    private val repositoryTour: RepositoryTour
) : ViewModel() {

    private val _liveTour = MutableLiveData<StateModel<DataTourInfo>>(StateModel.Initial)
    val liveTour : LiveData<StateModel<DataTourInfo>>
        get () = _liveTour

    fun getTourInfo(){
        _liveTour.value = StateModel.Loading
        viewModelScope.launch(Dispatchers.IO){
            try {
                _liveTour.postValue(
                    StateModel.Success(
                        repositoryTour.requestTourInfo()
                ))
            } catch (e : Exception){
                _liveTour.postValue(StateModel.Error(e))
            }
        }
    }

}