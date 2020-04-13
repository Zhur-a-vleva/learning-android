package logic

import data.artist.ArtistRepository
import ui.ArtistView

class ArtistPresenter(private val view: ArtistView) {

    fun onRefresh() {
        val data = ArtistRepository.MyAsyncTask().execute().get()
        view.showArtists(data)
    }
}