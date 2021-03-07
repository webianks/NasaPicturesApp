package com.webianks.nasapicturesapp.utils

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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

inline fun <reified T> String.toNasaPicturesList(gson: Gson): T =
    gson.fromJson<T>(this, object : TypeToken<T>() {}.type)