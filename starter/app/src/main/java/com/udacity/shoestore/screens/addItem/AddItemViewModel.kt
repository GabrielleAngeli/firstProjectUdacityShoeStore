package com.udacity.shoestore.screens.addItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class AddItemViewModel : ViewModel() {

    // Note: mutable LiveData for internal access
    private val _shoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())

    init {
        Timber.i("ViewModel Lifecycle started.")
    }

    // Note: expose LiveData (non-mutable)
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    // Note: callback before ViewModel is destroyed
    override fun onCleared() {
        super.onCleared()
        Timber.i("ViewModel Lifecycle Destroyed!")
    }

    fun saveValue(shoe: Shoe?) {
        shoe?.let { shoe ->
            _shoes.value?.add(shoe)
        }
    }

}