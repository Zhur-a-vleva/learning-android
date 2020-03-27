package logic

import data.Artist
import data.artist.ArtistRepository

class ArtistPresenter() {

    fun getData(): List<Artist> {
        return ArtistRepository.MyAsyncTask().execute().get()
    }

}