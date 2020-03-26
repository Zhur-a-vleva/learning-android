package data.song

import android.os.AsyncTask
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import data.Api
import data.Song
import kotlinx.android.synthetic.main.my_text_view.view.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SongRepository() {

    companion object {

        private var id: Int = 0

        fun setId(artistId: Int) {
            id = artistId
        }

        fun getId(): Int {
            return id
        }

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

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.text_in_recyclerView
    }

    class MyAsyncTask : AsyncTask<Unit, Unit, List<Song>>() {

        private val token = "YFTSs_-BbAkmrn16cEuQ-7mT1TXDpTKEASL66lDUBXRzIljYA6HBSdMfjxFcPfGA"

        override fun doInBackground(vararg params: Unit?): List<Song> {
            return getSongs(getId())
        }

        private fun getSongs(id: Int): List<Song> {
            val data = api.getSongData(id, token).execute()
            return data.body()?.response?.songs ?: emptyList()
        }

    }

}