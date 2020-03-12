package com.example.geniusapi.artists

import android.content.SharedPreferences
import com.example.geniusapi.ApiInterface
import com.example.geniusapi.Artist

interface ArtistsRepository {
    val api: ApiInterface // TODO разбить, чтобы было ArtistApi

    val localStorage: SharedPreferences

    fun getArtist(): List<Artist>
//            = {
//        return if(localStorage is empty) {
//            api.getArtist()
//        } else {
//            getFrom(localStorage)
//        }
//    }
}