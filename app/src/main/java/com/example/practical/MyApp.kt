package com.example.practical

import android.app.Application
import com.example.practical.api.RetrofitHelper
import com.example.practical.db.MovieDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: Application() {

    companion object{
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getApiInstance() = RetrofitHelper.provideApi(RetrofitHelper.provideRetrofit())

    fun getDatabase() = MovieDatabase.getDatabase()
}
