package com.example.android.musicsearchapp.response

import com.example.android.musicsearchapp.model.Song

data class SongsResponse(
    val results: List<Song>
)