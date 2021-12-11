package com.udacity.shoestore.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeDetailViewModel: ViewModel() {
    private val _saveButtonClicked = MutableLiveData<Boolean>()
    val saveButtonClicked: LiveData<Boolean> get() = _saveButtonClicked

    private val _cancelButtonClicked = MutableLiveData<Boolean>()
    val cancelButtonClicked: LiveData<Boolean> get() = _cancelButtonClicked


    fun saveClicked(){
        _saveButtonClicked.value = true
    }

    fun finishSaving(){
        _saveButtonClicked.value = false
    }

    fun cancelClicked(){
        _cancelButtonClicked.value = true
    }

    fun finishCanceling(){
        _cancelButtonClicked.value = false
    }
}