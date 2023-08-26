package com.example.practical.models

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide

@Entity(tableName = "movie_detail")
data class MovieDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val Actors: String,
    val Awards: String,
    val BoxOffice: String,
    val Country: String,
    val DVD: String,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Metascore: String,
    val Plot: String,
    val Poster: String,
    val Production: String,
    val Rated: String,
    val Ratings: List<Rating>,
    val Released: String,
    val Response: String,
    val Runtime: String,
    val Title: String,
    val Type: String,
    val Website: String,
    val Writer: String,
    val Year: String,
    val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String
)

@BindingAdapter("movieImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
//        .apply(RequestOptions().circleCrop())
        .into(view)
}
