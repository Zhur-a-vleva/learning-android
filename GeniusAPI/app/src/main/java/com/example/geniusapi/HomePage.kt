package com.example.geniusapi

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomePage : Activity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*> //<*> - что значит?
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setRecyclerView() Я не знаю, что делать
    }

    fun setRecyclerView(repository: ArtistRepository) {
        val data: Array<Song> = repository.getSongsFromArtist(16775)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(data)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

}