package com.webianks.nasapicturesapp.utils

import android.app.Application
import com.google.gson.Gson
import com.webianks.nasapicturesapp.data.NasaPicture
import java.io.IOException
import java.io.InputStream

fun Application.getAssetJsonData(file: String): String? {
    return try {
        val `is`: InputStream = assets.open(file)
        val size: Int = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        String(buffer)
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }
}

fun String.toNasaPicturesList(gson: Gson): List<NasaPicture> =
    gson.fromJson(this, Array<NasaPicture>::class.java).toList()