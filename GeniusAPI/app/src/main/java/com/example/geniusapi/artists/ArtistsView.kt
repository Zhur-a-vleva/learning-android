package com.example.geniusapi.artists

import com.example.geniusapi.Artist

interface ArtistsView {
    fun showArtists(artists: List<Artist>)
}