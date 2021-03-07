package com.webianks.nasapicturesapp.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.webianks.nasapicturesapp.data.NasaPicture
import com.webianks.nasapicturesapp.utils.UiState
import com.webianks.nasapicturesapp.utils.getAssetJsonData
import com.webianks.nasapicturesapp.utils.toNasaPicturesList


class MainViewModel(private val application: Application, private val gson: Gson) : ViewModel() {

    val picturesListState = MutableLiveData<UiState>()

    fun getPicturesList(): List<NasaPicture>? {
        val jsonArray = application.getAssetJsonData("data.json")
        return jsonArray?.toNasaPicturesList(gson)
    }
}