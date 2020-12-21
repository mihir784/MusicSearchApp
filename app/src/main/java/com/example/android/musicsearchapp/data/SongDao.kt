package com.example.android.musicsearchapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.android.musicsearchapp.model.Song

@Dao
interface SongDao {

    @Query("SELECT * FROM songs_db")
    fun getSongsFromDB(): List<Song>

    @Insert
    fun insertSongsToDB(vararg song: List<Song>)
}