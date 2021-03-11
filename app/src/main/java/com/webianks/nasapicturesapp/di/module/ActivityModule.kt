package com.webianks.nasapicturesapp.di.module

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.webianks.nasapicturesapp.data.PictureRepository
import com.webianks.nasapicturesapp.databinding.ActivityDetailsBinding
import com.webianks.nasapicturesapp.databinding.ActivityMainBinding
import com.webianks.nasapicturesapp.ui.main.MainActivity.Companion.GRID_SPAN_COUNT
import com.webianks.nasapicturesapp.ui.main.MainViewModel
import com.webianks.nasapicturesapp.ui.PicturesListAdapter
import com.webianks.nasapicturesapp.ui.details.DetailsViewModel
import com.webianks.nasapicturesapp.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideApplicationContext(): Application = activity.application

    @Provides
    fun provideGson() = Gson()

    @Provides
    fun provideGirdLayoutManager() = GridLayoutManager(activity, GRID_SPAN_COUNT)

    @Provides
    fun provideRecyclerViewAdapter() = PicturesListAdapter(emptyList())


    @Provides
    fun provideDetailActivityBinding() = ActivityDetailsBinding.inflate(activity.layoutInflater)

    @Provides
    fun provideMainActivityBinding() = ActivityMainBinding.inflate(activity.layoutInflater)

    @Provides
    fun getFileName() = "data.json"

    @Provides
    fun provideMainViewModel(
        application: Application,
        gson: Gson,
        fileName: String
    ): MainViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(PictureRepository(application, gson,fileName))
        }).get(MainViewModel::class.java)

    @Provides
    fun provideDetailsViewModel(
        application: Application,
        gson: Gson,
        fileName: String
    ): DetailsViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(DetailsViewModel::class) {
            DetailsViewModel(PictureRepository(application, gson,fileName))
        }).get(DetailsViewModel::class.java)
}