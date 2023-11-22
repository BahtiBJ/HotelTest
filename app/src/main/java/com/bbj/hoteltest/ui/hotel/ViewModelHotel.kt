package com.bbj.hoteltest.ui.hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbj.data.models.DataHotel
import com.bbj.data.models.StateModel
import com.bbj.data.repositories.RepositoryHotel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelHotel(
    private val repositoryHotel: RepositoryHotel
) : ViewModel() {

    private val _liveHotel = MutableLiveData<StateModel<DataHotel>>(StateModel.Initial)
    val liveHotel : LiveData<StateModel<DataHotel>>
        get () = _liveHotel

    fun getHotelInfo(){
        _liveHotel.value = StateModel.Loading
        viewModelScope.launch(Dispatchers.IO){
            try {
                _liveHotel.postValue(StateModel.Success(
                    repositoryHotel.requestHotelInfo()
                ))
            } catch (e : Exception){
                _liveHotel.postValue(StateModel.Error(e))
            }
        }
    }
}