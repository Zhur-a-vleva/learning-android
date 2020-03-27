package logic

import data.Song
import data.song.SongRepository

class SongPresenter {

    fun setId(id: Int) {
        SongRepository.setId(id)
    }

    fun getData(): List<Song> {
        return SongRepository.MyAsyncTask().execute().get()
    }
}