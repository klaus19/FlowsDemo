package com.example.flowsdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates

class SecondActivityViewmodel :ViewModel() {

    var number = 0
    private var _live = MutableLiveData<Int>()
    val liveData:LiveData<Int>
        get() = _live


       fun getData(){
           _live.value = number
       }



}