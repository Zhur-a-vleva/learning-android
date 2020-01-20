package com.example.geniusapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("artists/{id}")

    fun getArtist(@Path("id") artistId: Int, @Query("access_token") token: String):
            Call<Response>
}