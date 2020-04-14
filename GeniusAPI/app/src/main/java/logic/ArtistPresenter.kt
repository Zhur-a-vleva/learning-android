package logic

import android.annotation.SuppressLint
import data.artist.ArtistRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ui.ArtistView

class ArtistPresenter(private val view: ArtistView) {

    private val repository = ArtistRepository()

    @SuppressLint("CheckResult")
    fun onRefresh() {
        repository.getArtist(16777)
            .doOnSubscribe { view.showLoading() }
            .doOnSuccess { view.hideLoading() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showArtists(listOf(it))
            }, {
                view.showError(it)
            })
    }
}