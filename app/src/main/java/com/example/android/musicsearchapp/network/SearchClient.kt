package com.example.android.musicsearchapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchClient {

    fun getSearchRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}