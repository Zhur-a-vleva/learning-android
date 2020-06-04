package logic

import android.annotation.SuppressLint
import data.Description
import data.artist.ArtistRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ui.ArtistView


class ArtistPresenter(private val view: ArtistView) {

    private val repository = ArtistRepository()

    @SuppressLint("CheckResult")
    fun onRefresh() {
        repository.getArtist(16775)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view.showLoading()
            }
            .doOnSuccess { view.hideLoading() }
            .subscribe(
                { artist ->
                val newArtist = artist
                    .copy(
                        description = Description(
                            getFiveWords(
                                artist?.description?.text ?: ""
                            )
                        )
                    )
                view.showArtists(listOf(newArtist))
                },
                {
                view.showError(it)
                })
    }

    private fun getFiveWords(originText: String): String {
        return when (originText.split(" ").size) {
            in 0..4 -> "$originText ..."
            else -> "${originText.split(" ").subList(0, 5).joinToString(" ")} ..."
        }
    }

}