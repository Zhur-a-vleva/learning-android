package ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import data.Song
import kotlinx.android.synthetic.main.my_text_view.view.*
import logic.SongPresenter

class SongView : Fragment() {

    companion object {

        const val className = "SongView"
        private lateinit var context: Context
        private lateinit var recyclerView: RecyclerView

        fun newInstance(
            cont: Context,
            id: Int
        ): SongView {
            context = cont
            SongPresenter().setId(id)
            return SongView()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_layout, container, false)

        val data = SongPresenter().getData()

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MyAdapter(data, SongView.context) { url ->
            val browseIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browseIntent)
        }

        return view
    }

    class MyAdapter(
        private val data: List<Song>,
        private val context: Context,
        private val clickListener: (String) -> (Unit)
    ) :
        RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MyViewHolder {
            return MyViewHolder(
                (
                        LayoutInflater.from(context).inflate(R.layout.my_text_view, parent, false)
                        )
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

}