package com.udacity.shoestore.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    private val _canNavigateToWelcomeFragment = MutableLiveData<Boolean>()
    val canNavigateToWelcomeFragment: LiveData<Boolean> get() = _canNavigateToWelcomeFragment


    fun loginAndCreateAccount(){
        _canNavigateToWelcomeFragment.value = true
    }

    fun finishNavigate(){
        _canNavigateToWelcomeFragment.value = false
    }
}