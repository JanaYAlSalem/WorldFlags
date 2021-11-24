package com.example.worldflags.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldflags.network.FlagPhoto
import com.example.worldflags.network.flagApi
import kotlinx.coroutines.launch




class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    /*
* all list image
 */
    private val _photos = MutableLiveData<List<FlagPhoto>>()
    val photos: LiveData<List<FlagPhoto>> = _photos

    init {
        getflagPhotos()
    }


    private fun getflagPhotos() {
        viewModelScope.launch {
            try {
                /*
                 * OR we can write it directory like :
                 * for one image step 1 -> [0], all list use:
                 * _photos.value = MarsApi.retrofitService.getPhotos()
                 *  _status.value = "   First Mars image URL : ${_photos.value!!.imgSrcUrl}"
                 */
                val listResult = flagApi.retrofitService.getPhotos()
                _photos.value = listResult.data
                _status.value =  "Success: Mars properties retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}