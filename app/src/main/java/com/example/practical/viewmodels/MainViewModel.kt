package com.example.practical.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practical.api.APIResponseState
import com.example.practical.api.AppApis
import com.example.practical.db.MovieDatabase
import com.example.practical.models.MovieList
import com.example.practical.utils.isInternetAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: AppApis,
    private val database: MovieDatabase
) : ViewModel() {

    private val _getMovieList = MutableLiveData<APIResponseState<MovieList>>()
    val getMovieList
        get() = _getMovieList

    fun getMovieList(
        page: Int
    ) = viewModelScope.launch {

        try {

            val movieListFromDatabase = database.movieDao().getMovies()

            if (!isInternetAvailable()) {
                if (movieListFromDatabase != null) {
                    _getMovieList.value = APIResponseState.Success(movieListFromDatabase)
                    return@launch
                }
                _getMovieList.value = APIResponseState.Error("Internet not available")
                return@launch
            } else {
                val movieListResponse = api.getMovieList("e5311742", "Batman", page)
                if (movieListResponse.isSuccessful) {
                    database.movieDao().addMovies(movieListResponse.body()!!)
                    _getMovieList.value = APIResponseState.Success(movieListResponse.body())
                    return@launch
                }
                _getMovieList.value = APIResponseState.Error(movieListResponse.message())
            }

        } catch (e: Exception) {
            e.printStackTrace()
            _getMovieList.value = APIResponseState.Error("Something went wrong!")
        }

    }
}
