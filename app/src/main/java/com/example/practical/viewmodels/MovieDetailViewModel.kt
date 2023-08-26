package com.example.practical.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practical.api.APIResponseState
import com.example.practical.api.AppApis
import com.example.practical.db.MovieDatabase
import com.example.practical.models.MovieDetail
import com.example.practical.utils.isInternetAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val appApis: AppApis,
    private val database: MovieDatabase
) : ViewModel() {

    private val _getMovieDetail = MutableLiveData<APIResponseState<MovieDetail>>()
    val getMovieDetail
        get() = _getMovieDetail

    fun getMovieDetail(movieId: String) = viewModelScope.launch {

        if (!isInternetAvailable()) {
            _getMovieDetail.value = APIResponseState.Error("Internet not available")
            return@launch
        }

        try {
            val movieDetail = appApis.getMovieDetail("e5311742", movieId)

            if (movieDetail.isSuccessful) {
                _getMovieDetail.value = APIResponseState.Success(movieDetail.body())
                return@launch
            }

            _getMovieDetail.value = APIResponseState.Error(movieDetail.message())
        } catch (e: Exception) {
            e.printStackTrace()
            _getMovieDetail.value = APIResponseState.Error("Something went wrong!")
        }

    }

}
