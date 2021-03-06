package com.webianks.nasapicturesapp.utils

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.webianks.nasapicturesapp.R
import com.webianks.nasapicturesapp.data.NasaPicture


private inline fun <reified T> Context.readRawJson(@RawRes rawResId: Int, gson: Gson): T {
    resources.openRawResource(rawResId).bufferedReader().use {
        return gson.fromJson<T>(it, object : TypeToken<T>() {}.type)
    }
}

//TODO remove
fun getPicturesList(context: Context) : List<NasaPicture> = context.readRawJson(R.raw.data, Gson())
