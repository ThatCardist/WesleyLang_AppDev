package com.example.watchlist

import android.content.ClipData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: Movie)

    @Update
    fun update(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("SELECT * from movie WHERE uid = :id")
    fun getItem(id: Int): Movie

    @Query("SELECT * from movie")
    fun getItems(): List<Movie>
    //Flow<List<Movie>>
}
