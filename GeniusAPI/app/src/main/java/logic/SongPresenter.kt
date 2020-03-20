package logic

import android.view.View
import data.Song
import data.song.SongRepository

class SongPresenter {

    fun setId(id: Int) {
        SongRepository.setId(id)
    }

    fun getData(): List<Song> {
        return SongRepository.MyAsyncTask().execute().get()
    }

    fun getViewHolder(view: View): SongRepository.MyViewHolder {
        return SongRepository.MyViewHolder(view)
    }
}