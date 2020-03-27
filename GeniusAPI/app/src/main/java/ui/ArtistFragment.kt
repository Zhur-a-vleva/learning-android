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

class ArtistFragment : Fragment(), ArtistView {

    private var adapter: MyAdapter? = null

    companion object {

        const val className = "ArtistView"
        private lateinit var context: Context
        private lateinit var recyclerView: RecyclerView

        fun newInstance(cont: Context): ArtistFragment {
            context = cont
            return ArtistFragment()
        }
    }

    // FIXME: соотв. логику перенести в onViewCreated()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_layout, container, false)
        val fragmentTransaction = fragmentManager?.beginTransaction()

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        this.adapter = MyAdapter(null, ArtistFragment.context) { id ->
            fragmentTransaction
                    ?.replace(
                            R.id.fragment_container,
                            SongView.newInstance(ArtistFragment.context, id)
                    )
                    ?.commit()
            fragmentTransaction?.addToBackStack(SongView.className)
        }

        recyclerView.adapter = this.adapter

        val presenter = ArtistPresenter(this)
        presenter.onRefresh()

        return view
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.text_in_recyclerView
    }

    override fun showArtists(data: List<Artist>) {
        adapter?.submitData(data)
    }
}

class MyAdapter(
        private var data: List<Artist>?,
        private val context: Context,
        private val clickListener: (Int) -> Unit
) :
        RecyclerView.Adapter<ArtistFragment.MyViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ArtistFragment.MyViewHolder {
        return ArtistFragment.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.my_text_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArtistFragment.MyViewHolder, position: Int) {
        holder.textView.text = data?.get(position)?.name
        holder.textView.setOnClickListener {
            clickListener(data?.get(position)?.id ?: 0)
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    fun submitData(data: List<Artist>) {
        this.data = data
        notifyDataSetChanged()
    }
}

interface ArtistView {
    fun showArtists(data: List<Artist>)
}