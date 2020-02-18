package com.example.geniusapi

import android.content.Context
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

class ArtistsFragment : Fragment() {

    class MyAsyncTask : AsyncTask<Unit, Unit, Array<Artist>>() {

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

        override fun doInBackground(vararg params: Unit?): Array<Artist> {
            return arrayOf(
                ArtistRepository(api).getArtist(16777),
                ArtistRepository(api).getArtist(16772),
                ArtistRepository(api).getArtist(16775),
                ArtistRepository(api).getArtist(16771),
                ArtistRepository(api).getArtist(16773)
            )
        }

    }

    companion object {

        const val className = "ArtistsFragment"
        private lateinit var context: Context
        private lateinit var recyclerView: RecyclerView

        fun newInstance(cont: Context): ArtistsFragment {
            context = cont
            return ArtistsFragment()
        }

    }

    class MyAdapter(
        private val data: Array<Artist>,
        private val context: Context,
        private val clickListener: (Int) -> Unit
    ) :
        RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.my_text_view, parent, false)
            )
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.textView.text = data[position].name
            holder.textView.setOnClickListener {
                clickListener(data[position].id)
            }
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
        val view: View = inflater.inflate(R.layout.fragment_layout, container, false)
        val asyncTask = MyAsyncTask().execute()
        val data = asyncTask.get()
        val fragmentTransaction = fragmentManager?.beginTransaction()

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(ArtistsFragment.context)
        recyclerView.adapter = MyAdapter(data, ArtistsFragment.context) { id ->
            fragmentTransaction
                ?.replace(
                    R.id.fragment_container,
                    SongsFragment.newInstance(ArtistsFragment.context, id)
                )
                ?.commit()
            fragmentTransaction?.addToBackStack(SongsFragment.className)
        }

        return view
    }
}