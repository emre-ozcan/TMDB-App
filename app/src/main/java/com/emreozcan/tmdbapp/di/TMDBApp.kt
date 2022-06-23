package com.emreozcan.tmdbapp.di

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by @Emre Özcan on 22.06.2022
 */
@HiltAndroidApp
class TMDBApp : Application(){
    companion object {
        private lateinit var instance: TMDBApp
        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}