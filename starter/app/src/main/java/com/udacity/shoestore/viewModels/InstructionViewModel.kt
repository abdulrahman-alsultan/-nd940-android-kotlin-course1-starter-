package com.udacity.shoestore.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionViewModel: ViewModel() {
    private val _canNavigateToShoeList = MutableLiveData<Boolean>()
    val canNavigateToShoeList: LiveData<Boolean> get() = _canNavigateToShoeList

    fun navigateButtonClicked(){
        _canNavigateToShoeList.value = true
    }

    fun finishNavigate(){
        _canNavigateToShoeList.value = false
    }

}