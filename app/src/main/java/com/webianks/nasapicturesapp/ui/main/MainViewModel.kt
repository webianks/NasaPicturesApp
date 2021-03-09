package com.webianks.nasapicturesapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.webianks.nasapicturesapp.R
import com.webianks.nasapicturesapp.data.PictureRepository
import com.webianks.nasapicturesapp.utils.*

class MainViewModel(private val pictureRepository: PictureRepository) : ViewModel() {

    val picturesListState = MutableLiveData<UiState>()

    fun getPicturesList() {
        pictureRepository.getPicturesList({
            picturesListState.postValue(Success(it))
        }, {
            picturesListState.postValue(Error(R.string.default_error_msg))
        })
    }
}