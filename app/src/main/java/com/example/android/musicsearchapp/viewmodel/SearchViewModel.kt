package com.example.android.musicsearchapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android.musicsearchapp.repository.SearchSongsRepository
import com.example.android.musicsearchapp.response.SongsResponse

class SearchViewModel(): ViewModel() {
    private val searchSongsRepository = SearchSongsRepository()

    fun searchSongs(searchTerm: String): LiveData<SongsResponse> {
        return searchSongsRepository.searchSongs(searchTerm)
    }
}