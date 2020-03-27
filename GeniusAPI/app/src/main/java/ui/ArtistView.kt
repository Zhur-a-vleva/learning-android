package ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import data.Artist
import kotlinx.android.synthetic.main.my_text_view.view.*
import logic.ArtistPresenter

class ArtistView : Fragment() {

    companion object {

        const val className = "ArtistView"
        private lateinit var context: Context
        private lateinit var recyclerView: RecyclerView

        fun newInstance(cont: Context): ArtistView {
            context = cont
            return ArtistView()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_layout, container, false)
        val data = ArtistPresenter().getData()
        val fragmentTransaction = fragmentManager?.beginTransaction()

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MyAdapter(data, ArtistView.context) { id ->
            fragmentTransaction
                ?.replace(
                    R.id.fragment_container,
                    SongView.newInstance(ArtistView.context, id)
                )
                ?.commit()
            fragmentTransaction?.addToBackStack(SongView.className)
        }

        return view
    }

    class MyAdapter(
        private val data: List<Artist> = ArtistPresenter().getData(),
        private val context: Context,
        private val clickListener: (Int) -> Unit
    ) :
        RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MyViewHolder {
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
}