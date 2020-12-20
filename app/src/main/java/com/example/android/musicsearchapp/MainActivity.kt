package com.example.android.musicsearchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.android.musicsearchapp.model.Song
import com.example.android.musicsearchapp.viewmodel.SearchViewModel
import com.example.android.musicsearchapp.adapter.RecyclerViewAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var songs = ArrayList<Song>()
    private lateinit var textView: TextView
    private lateinit var editText: EditText
    private lateinit var btnSearch: ImageButton
    private lateinit var searchTerm: String
    private lateinit var recyclerView: RecyclerView
    var recyclerViewAdapter = RecyclerViewAdapter()
    private lateinit var searchViewModel: SearchViewModel
    //private lateinit var SongsDB: SongDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //SongsDB = SongDatabase(this)//Room.databaseBuilder(this, SongDatabase::class.java,"songs_db").build()
        setUpViews()
    }

    private fun setUpViews() {
        textView = findViewById(R.id.text_view_error)
        editText = findViewById(R.id.edit_text_search)
        btnSearch = findViewById(R.id.btn_search)
        recyclerView = findViewById(R.id.recycler_view_searchResult)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.adapter = recyclerViewAdapter
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        btnSearch.setOnClickListener {
            searchTerm = editText.text.toString()
            if (searchTerm.isNullOrEmpty()) {
                Toast.makeText(this, "Enter a Name to search", Toast.LENGTH_LONG).show()
            } else {
                searchSongs(searchTerm)
            }
        }
    }

    private fun searchSongs(searchTerm: String) {
        /*songs = searchFromDB(searchTerm)
        if(songs.isNotEmpty()) {
            Toast.makeText(this,"Songs From DB",Toast.LENGTH_LONG).show()
            recyclerView.visibility = View.VISIBLE
            textView.visibility = View.INVISIBLE
            recyclerViewAdapter.setListData(songs)
            recyclerViewAdapter.notifyDataSetChanged()
        } else{
            Toast.makeText(this,"Songs From API",Toast.LENGTH_LONG).show()
        */
            searchViewModel.searchSongs(searchTerm).observe(this, Observer {
                if(it != null) {
                    recyclerView.visibility = View.VISIBLE
                    textView.visibility = View.INVISIBLE
                    recyclerViewAdapter.setListData(it.results)
                    recyclerViewAdapter.notifyDataSetChanged()
                    //insetSongsToDB(it.results)
                } else {
                    recyclerView.visibility = View.INVISIBLE
                    textView.visibility = View.VISIBLE
                }
            })
        //}
    }

    /*private fun insetSongsToDB(songs: List<Song>) {
        GlobalScope.launch {
            SongsDB.songDao().insertSongsToDB(songs)
        }
    }

    private fun searchFromDB(searchTerm: String): ArrayList<Song> {
        var songsList = ArrayList<Song>()
        GlobalScope.launch {
            songsList = SongsDB.songDao().getSongsFromDB() as ArrayList<Song>
        }
        var songsResultList = ArrayList<Song>()
        for(song: Song in songsList){
            if(song.artistName.equals(searchTerm)){
                songsResultList.add(song)
            }
        }
        return songsList
    }*/
}