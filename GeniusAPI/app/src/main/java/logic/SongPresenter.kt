package logic

import data.song.SongRepository
import ui.SongView

class SongPresenter(private val view: SongView) {

    fun onRefresh() {
        val data = SongRepository.MyAsyncTask().execute().get()
        view.showSongs(data)
    }

}