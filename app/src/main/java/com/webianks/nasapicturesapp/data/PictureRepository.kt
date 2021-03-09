package com.webianks.nasapicturesapp.data

import android.app.Application
import com.google.gson.Gson
import com.webianks.nasapicturesapp.utils.getAssetJsonData
import com.webianks.nasapicturesapp.utils.toNasaPicturesList
import java.lang.Exception

class PictureRepository(private val application: Application, private val gson: Gson) {

    /**
     * Mimicking network response here with success and error callback
     */
    fun getPicturesList(success: (List<NasaPicture>) -> Unit,error: (Exception) -> Unit) {
        val jsonArray = application.getAssetJsonData("data.json")
        val list = jsonArray?.toNasaPicturesList(gson)
        list?.let { it ->
            val arrayList = ArrayList(it)
            arrayList.sortByDescending { picture -> picture.date }
            success(arrayList)
        } ?: kotlin.run {
            error(Exception())
        }
    }
}