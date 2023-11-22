package com.bbj.hoteltest.ui.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbj.data.models.DataHotel
import com.bbj.data.models.DataRoomList
import com.bbj.data.models.StateModel
import com.bbj.data.repositories.RepositoryHotel
import com.bbj.data.repositories.RepositoryRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelRooms(
    private val repositoryRoom: RepositoryRoom
) : ViewModel() {

    private val _liveRoom = MutableLiveData<StateModel<DataRoomList>>(StateModel.Initial)
    val liveRoom : LiveData<StateModel<DataRoomList>>
        get () = _liveRoom

    fun getRoomsList(){
        _liveRoom.value = StateModel.Loading
        viewModelScope.launch(Dispatchers.IO){
            try {
                _liveRoom.postValue(
                    StateModel.Success(
                        repositoryRoom.requestRooms()
                ))
            } catch (e : Exception){
                _liveRoom.postValue(StateModel.Error(e))
            }
        }
    }

}