package com.bbj.data.models

sealed class StateModel<out T>  {

    class Success<T>(val data : T) : StateModel<T>()

    data object Loading : StateModel<Nothing>()

    class Error(val error : Exception)  : StateModel<Nothing>()

    data object Initial : StateModel<Nothing>()

    fun ifSuccess(run : (T) -> Unit){
        if (this is Success)
            run(this.data)
    }

    fun ifLoading(run : () -> Unit){
        if (this is Loading)
            run()
    }
    fun ifInitial(run : () -> Unit){
        if (this is Initial)
            run()
    }

    fun ifError(run : (Exception) -> Unit){
        if (this is Error)
            run(this.error)
    }

}