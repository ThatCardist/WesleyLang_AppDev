package com.example.watchlist
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    @ColumnInfo(name = "title")
    var title: String = "movieTitle",
    @ColumnInfo(name = "streamingService")
    var service: String,
    @ColumnInfo(name = "movieRating")
    var rating: Int?,
    @ColumnInfo(name = "haveSeen")
    var seen: Boolean?
)