package com.webianks.nasapicturesapp.data

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PictureRepositoryTest {

    private val context: Context = ApplicationProvider.getApplicationContext()
    private lateinit var pictureRepository: PictureRepository
    private lateinit var pictureRepositoryFailing: PictureRepository

    @Before
    fun init() {
        pictureRepository = PictureRepository(context as Application, Gson(),"data.json")
        pictureRepositoryFailing = PictureRepository(context, Gson(),"failing.json")
    }

    @Test
    fun testPictureListSuccess() {
        pictureRepository.getPicturesList({
            assert(it.isNotEmpty())
            assert(it.size == 26)
        })
    }

    @Test
    fun testPictureListError() {
        pictureRepositoryFailing.getPicturesList({
        },{
            checkNotNull(it)
        })
    }

}