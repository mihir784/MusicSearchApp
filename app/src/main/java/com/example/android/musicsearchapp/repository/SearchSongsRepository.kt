package com.example.android.musicsearchapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.musicsearchapp.network.SearchApiService
import com.example.android.musicsearchapp.network.SearchClient
import com.example.android.musicsearchapp.response.SongsResponse
import retrofit2.Call
import retrofit2.Response

class SearchSongsRepository() {
    private val searchApiService: SearchApiService = SearchClient().getSearchRetrofit().create(SearchApiService::class.java)

    fun searchSongs(searchTerm: String): LiveData<SongsResponse>{
        val data: MutableLiveData<SongsResponse> = MutableLiveData<SongsResponse>()
        searchApiService.searchSongs(searchTerm).enqueue(object : retrofit2.Callback<SongsResponse>{
            override fun onResponse(call: Call<SongsResponse>, response: Response<SongsResponse>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<SongsResponse>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
}