package com.example.geniusapi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArtistRepositoryTest {

    private val okHttpClient = OkHttpClient.Builder()
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

    private val repository = ArtistRepository(api)

    @Test
    fun getArtist_test() {
        val artist: Artist = repository.getArtist(16775)
        println(artist.name + " " + artist.id)
    }

    @Test
    fun getSongsFromArtist_test() {
        val songs: Array<Song>? = repository.getSongsFromArtist(16775)
    }

}