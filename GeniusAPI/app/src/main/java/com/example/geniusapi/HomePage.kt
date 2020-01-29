package com.example.geniusapi

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomePage : Activity() {

    private lateinit var recyclerView: RecyclerView

    class MyAsyncTask : AsyncTask<Unit, Unit, Array<Song>>() {

        override fun doInBackground(vararg params: Unit?): Array<Song>? {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.HEADERS)
                })
                .build()

            val api: ApiInterface = Retrofit.Builder()
                .baseUrl("http://api.genius.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)

            return ArtistRepository(api).getSongsFromArtist(16775)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val asyncTask = MyAsyncTask().execute()
        val data = asyncTask.get()

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter(data, this)
    }

}