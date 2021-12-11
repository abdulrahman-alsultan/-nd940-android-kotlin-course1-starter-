package com.udacity.shoestore.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel: ViewModel() {
    private val _canNavigateToInstructionFragment = MutableLiveData<Boolean>()
    val canNavigateToInstructionFragment: LiveData<Boolean> get() = _canNavigateToInstructionFragment

    fun navigateButtonClicked(){
        _canNavigateToInstructionFragment.value = true
    }

    fun finishNavigate(){
        _canNavigateToInstructionFragment.value = false
    }
}