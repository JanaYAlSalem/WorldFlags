package com.example.worldflags.Overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldflags.network.flagApi
import com.example.worldflags.network.flagApiService
import kotlinx.coroutines.launch




class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()

    val status: LiveData<String> = _status

    init {
        getflagPhotos()
    }


    private fun getflagPhotos() {
        viewModelScope.launch {
            try {
                val listResult = flagApi.retrofitService.getPhotos()
                _status.value = "Success: ${listResult.data.size} photos retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}