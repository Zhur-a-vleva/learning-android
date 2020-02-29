package com.example.geniusapi

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.my_text_view.view.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SongsFragment : Fragment() {

    class MyAsyncTask : AsyncTask<Unit, Unit, ArrayList<Song>>() {

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.HEADERS)
            })
            .build()

        private val api: ApiInterface = Retrofit.Builder()
            .baseUrl("http://api.genius.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        override fun doInBackground(vararg params: Unit?): ArrayList<Song> {
            return ArtistRepository(api).getSongsFromArtist(id)
        }

    }

    companion object {

        const val className = "SongsFragment"
        private lateinit var context: Context
        private lateinit var recyclerView: RecyclerView
        private var id: Int = 0

        fun newInstance(
            cont: Context,
            artistsId: Int
        ): SongsFragment {
            context = cont
            id = artistsId
            return SongsFragment()
        }
    }

    class MyAdapter(
        private val data: ArrayList<Song>,
        private val context: Context,
        private val clickListener: (String) -> (Unit)
    ) :
        RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.my_text_view, parent, false)
            )
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.textView.text = data[position].title
            holder.textView.setOnClickListener { clickListener(data[position].url) }
        }

        override fun getItemCount(): Int {
            return data.size
        }

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.text_in_recyclerView
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_layout, container, false)

        val asyncTask = MyAsyncTask().execute()
        val data = asyncTask.get()

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(SongsFragment.context)
        recyclerView.adapter = MyAdapter(data, SongsFragment.context) { url ->
            val browseIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browseIntent)
        }

        return view
    }

}