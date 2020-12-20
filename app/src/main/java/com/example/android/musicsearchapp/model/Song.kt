package com.example.android.musicsearchapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs_db")
data class Song(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="trackName")
    val trackName: String?,
    @ColumnInfo(name="collectionName")
    val collectionName: String?,
    @ColumnInfo(name="artistName")
    val artistName: String?,
    @ColumnInfo(name="artworkUrl100")
    val artworkUrl100: String?
)
