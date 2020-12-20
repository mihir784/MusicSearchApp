package com.example.android.musicsearchapp.network

import com.example.android.musicsearchapp.response.SongsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET("search")
    fun searchSongs(
        @Query("term") term: String,
        @Query("media") media: String = "music"
    ): Call<SongsResponse>
}