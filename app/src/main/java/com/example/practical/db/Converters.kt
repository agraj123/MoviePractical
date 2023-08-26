package com.example.practical.db

import androidx.room.TypeConverter
import com.example.practical.models.Rating
import com.example.practical.models.Search
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun fromRatingToString(rating: List<Rating>): String {
        return Gson().toJson(rating)
    }

    @TypeConverter
    fun fromStringToRating(rating: String): List<Rating> {
        return Gson().fromJson(rating, Array<Rating>::class.java).toList()
    }

    @TypeConverter
    fun fromSearchListToString(search: List<Search>): String{
        return Gson().toJson(search)
    }

    @TypeConverter
    fun fromStringToSearchList(search: String): List<Search> {
        return Gson().fromJson(search, Array<Search>::class.java).toList()
    }
}
