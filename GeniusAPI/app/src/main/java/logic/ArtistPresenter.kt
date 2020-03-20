package logic

import android.view.View
import data.Artist
import data.artist.ArtistRepository

class ArtistPresenter() {

    fun getData(): List<Artist> {
        return ArtistRepository.MyAsyncTask().execute().get()
    }

    fun getViewHolder(view: View): ArtistRepository.MyViewHolder {
        return ArtistRepository.MyViewHolder(view)
    }
}