package com.example.practical.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practical.api.AppApis
import com.example.practical.db.MovieDatabase

class ViewModelFactory(private val appApis: AppApis, private val database: MovieDatabase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                appApis,
                database
            ) as T

            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> MovieDetailViewModel(
                appApis, database
            ) as T

            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }

}
