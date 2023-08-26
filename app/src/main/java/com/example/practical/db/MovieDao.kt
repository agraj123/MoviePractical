package com.example.practical.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.practical.models.MovieDetail
import com.example.practical.models.MovieList

@Dao
interface MovieDao {

    @Insert
    suspend fun addMovies(movies: MovieList)

    @Insert
    suspend fun addMovieDetail(movieDetail: MovieDetail)

    @Query("SELECT * FROM movie")
    suspend fun getMovies(): MovieList?

}
