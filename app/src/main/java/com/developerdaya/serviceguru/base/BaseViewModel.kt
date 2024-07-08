package com.developerdaya.serviceguru.base


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developerdaya.serviceguru.util.Retrofit
import com.developerdaya.serviceguru.util.ApiInterface


abstract class BaseViewModel : ViewModel() {
    val throwable = MutableLiveData<Throwable?>()
    val success = MutableLiveData<Any>()

    val api : ApiInterface by lazy {
        Retrofit.createBaseApiService()
    }

    fun onResponseError(it: Throwable?) {
        throwable.postValue(it)
    }

}