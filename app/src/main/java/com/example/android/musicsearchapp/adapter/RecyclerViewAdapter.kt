package com.example.android.musicsearchapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.musicsearchapp.R
import com.example.android.musicsearchapp.model.Song

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var songs = ArrayList<Song>()

    fun setListData(data: List<Song>) {
        this.songs = data as ArrayList<Song>
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_search_song, parent, false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(songs[position])
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val textViewSongName: TextView = view.findViewById(R.id.text_view_Song_Name)
        private val textViewAlbumName: TextView = view.findViewById(R.id.text_view_Album_Name)
        private val imageViewSong: ImageView = view.findViewById(R.id.image_view_song)

        fun bind(song: Song) {
            textViewSongName.text = song.trackName
            textViewAlbumName.text = song.collectionName

            Glide.with(imageViewSong)
                .load(song.artworkUrl100)
                .placeholder(R.drawable.ic_loading_image)
                .error(R.drawable.ic_image_error)
                .fallback(R.drawable.ic_image)
                .into(imageViewSong)

        }
    }
}