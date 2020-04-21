package logic

import android.annotation.SuppressLint
import data.song.SongRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ui.SongView

class SongPresenter(private val view: SongView) {

    private val repository = SongRepository()

    companion object {
        var id = 0
    }

    @SuppressLint("CheckResult")
    fun onRefresh() {
        repository.getSongs(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showLoading() }
            .doOnSuccess { view.hideLoading() }
            .subscribe({
                view.showSongs(it)
            }, {
                view.showError(it)
            })
    }

}