package com.webianks.nasapicturesapp.di.component

import com.webianks.nasapicturesapp.di.module.ActivityModule
import com.webianks.nasapicturesapp.ui.details.DetailsActivity
import com.webianks.nasapicturesapp.ui.main.MainActivity
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent{

    fun inject(mainActivity: MainActivity)

    fun inject(detailsActivity: DetailsActivity)
}