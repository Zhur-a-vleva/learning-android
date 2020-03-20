package data.artist

import android.os.AsyncTask
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import data.Api
import data.Artist
import kotlinx.android.synthetic.main.my_text_view.view.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArtistRepository() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.text_in_recyclerView
    }

    class MyAsyncTask : AsyncTask<Unit, Unit, List<Artist>>() {

        private val token = "YFTSs_-BbAkmrn16cEuQ-7mT1TXDpTKEASL66lDUBXRzIljYA6HBSdMfjxFcPfGA"

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.HEADERS)
            })
            .build()

        private val api: Api = Retrofit.Builder()
            .baseUrl("http://api.genius.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        private fun getArtist(id: Int): Artist {
            val data = api.getArtistData(id, token).execute()
            return data.body()?.response?.artist ?: Artist("Artist", 0)
        }

        override fun doInBackground(vararg params: Unit?): List<Artist> {
            return listOf(
                getArtist(16777),
                getArtist(16772), getArtist(16775), getArtist(16771), getArtist(16773)
            )
        }

    }
}