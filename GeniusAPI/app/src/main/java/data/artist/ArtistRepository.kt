package data.artist

import android.graphics.Bitmap
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import data.Api
import data.Artist
import data.ImageServer
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ArtistRepository {
    private val token = "YFTSs_-BbAkmrn16cEuQ-7mT1TXDpTKEASL66lDUBXRzIljYA6HBSdMfjxFcPfGA"

    companion object {
        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.HEADERS)
            })
            .build()

        private val api: Api = Retrofit.Builder()
            .baseUrl("http://api.genius.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(Api::class.java)

        private val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        private val imageServer: ImageServer = Retrofit.Builder()
            .baseUrl("https://images.genius.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ImageServer::class.java)
    }

    fun getArtist(id: Int): Single<Artist> {
        return api.getArtistData(id, token)
            .map { data -> data.response.artist }
    }

    fun getPhoto(url: String): Single<Bitmap> {
        return imageServer.getArtistPhoto(url.removePrefix("https://images.genius.com/"))
    }
}