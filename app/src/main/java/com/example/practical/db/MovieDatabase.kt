package com.example.practical.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.practical.MyApp
import com.example.practical.models.MovieDetail
import com.example.practical.models.MovieList

@Database(entities = [MovieList::class, MovieDetail::class], version = 2)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(): MovieDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        MyApp.instance,
                        MovieDatabase::class.java,
                        "movieDB"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
