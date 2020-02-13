package com.example.geniusapi

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.my_text_view.view.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//почему между элементами большое расстояние в recyclerView?
// FIXME: у меня чё-то вообще ничего не отобразилось

class ArtistsFragment : Fragment() {

    class MyAsyncTask : AsyncTask<Unit, Unit, Array<Artist>>() {

        override fun doInBackground(vararg params: Unit?): Array<Artist> {
            // TODO: окхттп клиент будет создававться каждый раз, когда мы делаем запрос в сеть.
            // Лучше бы выносить создание тяжёлых объектов в поле класса
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.HEADERS)
                })
                .build()

            val api: ApiInterface = Retrofit.Builder()
                .baseUrl("http://api.genius.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
            // FIXME: везде, где можно использовать 'val` - стараться исользовать именно его. 'var' вообще плохая тема
            var artistsArray = arrayOf(
                ArtistRepository(api).getArtist(16777),
                ArtistRepository(api).getArtist(16772),
                ArtistRepository(api).getArtist(16775),
                ArtistRepository(api).getArtist(16771),
                ArtistRepository(api).getArtist(16773)
            )
            return artistsArray
        }

    }

    companion object {

        // FIXME: вам идея подсказывает, подчёркивая зелёным - можно сделать константу. ЛКМ на имя переменной и ALT+ENTER
        val className = "ArtistsFragment"
        private lateinit var fragmentTransaction: FragmentTransaction
        private lateinit var context: Context
        private lateinit var recyclerView: RecyclerView

        fun newInstance(cont: Context, transaction: FragmentTransaction): ArtistsFragment {
            context = cont
            fragmentTransaction = transaction
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
        // FIXME: тут тоже идея подсказывает, что текствью может быть null, лучше задать тип переменной, как nullable 'TextView?'
        val textView = view.text_in_recyclerView
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // FIXME: поменять на val
        var view: View = inflater.inflate(R.layout.fragment_layout, container, false)

        val asyncTask = MyAsyncTask().execute()
        val data = asyncTask.get()

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(ArtistsFragment.context)
        recyclerView.adapter = MyAdapter(data, ArtistsFragment.context, clickListener = { id ->
            // TODO: implement
        })

        recyclerView.setOnClickListener {
            //Как понять, какой элемент был выбран?
            // FIXME: это клик листенер для всего RecyclerView, обработку клика на элементах надо делать в адаптере
        }

        return view
    }
}