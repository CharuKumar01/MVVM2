package com.example.mvvm2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountViewModel: ViewModel() {
    private var _count = MutableLiveData(0)
    val count: LiveData<Int> get() = _count

    fun increment(){
        _count.value = _count.value?.plus(1)
    }
}