package com.udacity.shoestore.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    private val _navigateToDetails = MutableLiveData<Boolean>()
    val navigateToDetails: LiveData<Boolean> get() = _navigateToDetails

    val newShoe = MutableLiveData<Shoe?>()

    init{
        _shoeList.value = mutableListOf(
                Shoe("ALTASPORT SHOES", 7.5, "Adidas", "They'll be ready for school or play in these classic court-inspired kids' shoes. Built to last, the sneakers have a flexible synthetic leather upper and a breathable mesh tongue. Hook-and-loop straps let your young adventurer get them on and off in a flash.")
        )
        newShoe.value = Shoe("", 0.0, "", "")
    }

    fun addButtonClick(){
        _navigateToDetails.value = true
    }

    fun finishNavigate(){
        _navigateToDetails.value = false
    }

    fun addNewShoe(shoe: Shoe){
        _shoeList.value?.add(shoe)
    }

    fun clear(){
        _shoeList.value?.clear()
    }

    fun getShoeList(): LiveData<MutableList<Shoe>>{
        return _shoeList
    }

    ///////////////////

    private val _saveButtonClicked = MutableLiveData<Boolean>()
    val saveButtonClicked: LiveData<Boolean> get() = _saveButtonClicked

    private val _cancelButtonClicked = MutableLiveData<Boolean>()
    val cancelButtonClicked: LiveData<Boolean> get() = _cancelButtonClicked




    fun saveClicked(){
        if(newShoe.value?.name != "" && newShoe.value?.company != "" && newShoe.value?.description != "" && newShoe.value?.size != 0.0){
            _shoeList.value?.add(newShoe.value!!)
            newShoe.value = Shoe("", 0.0, "", "")


            _saveButtonClicked.value = true
        }
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