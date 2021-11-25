package com.example.worldflags.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldflags.network.FlagPhoto
import com.example.worldflags.network.flagApi
import kotlinx.coroutines.launch



enum class FlagsApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    /*
    * status of notworks
     */
    private val _status = MutableLiveData<FlagsApiStatus>()
    val status: LiveData<FlagsApiStatus> = _status

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
            _status.value = FlagsApiStatus.LOADING // loading in first call of getflagPhotos.
            try {
                /*
                 * OR we can write it directory like :
                 * for one image step 1 -> [0], all list use:
                 * _photos.value = MarsApi.retrofitService.getPhotos()
                 *  _status.value = "   First Mars image URL : ${_photos.value!!.imgSrcUrl}"
                 */
                val listResult = flagApi.retrofitService.getPhotos()
                _photos.value = listResult.data
                _status.value = FlagsApiStatus.DONE // when data is returned
            } catch (e: Exception) {
                _status.value = FlagsApiStatus.ERROR // when data is not return
                _photos.value = listOf()

            }
        }
    }
}