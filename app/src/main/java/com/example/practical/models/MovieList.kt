package com.example.practical.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieList(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)
