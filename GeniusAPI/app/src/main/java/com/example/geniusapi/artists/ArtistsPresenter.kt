package com.example.geniusapi.artists

interface ArtistsPresenter {
    val repository: ArtistsRepository
    val view: ArtistsView

    fun onRefresh()
}
