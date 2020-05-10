package data

import android.graphics.Bitmap
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageServer {

    @GET("{url}")

    fun getArtistPhoto(@Path("url") url: String): Single<Bitmap>
}