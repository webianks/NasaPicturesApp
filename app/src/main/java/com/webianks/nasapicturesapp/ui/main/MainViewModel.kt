package com.webianks.nasapicturesapp.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.webianks.nasapicturesapp.utils.*
import kotlin.collections.ArrayList

class MainViewModel(private val application: Application, private val gson: Gson) : ViewModel() {

    val picturesListState = MutableLiveData<UiState>()

    fun getPicturesList(){
        val jsonArray = application.getAssetJsonData("data.json")
        val list = jsonArray?.toNasaPicturesList(gson)
        list?.let { it ->
            val arrayList =  ArrayList(it)
            arrayList.sortByDescending{ picture -> picture.date}
            picturesListState.postValue(Success(arrayList))
        }
    }
}