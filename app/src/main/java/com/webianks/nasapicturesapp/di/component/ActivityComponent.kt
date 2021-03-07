package com.webianks.nasapicturesapp.di.component

import com.webianks.nasapicturesapp.di.module.ActivityModule
import com.webianks.nasapicturesapp.ui.MainActivity
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent{

    fun inject(mainActivity: MainActivity)

}