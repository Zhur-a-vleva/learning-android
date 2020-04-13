package ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import data.Artist
import data.song.SongRepository
import kotlinx.android.synthetic.main.my_text_view.view.*
import logic.ArtistPresenter

class ArtistFragment : Fragment(R.layout.fragment_layout), ArtistView {

    private lateinit var adapter: ArtistAdapter

    companion object {

        const val className = "ArtistView"
        private lateinit var context: Context
        private lateinit var recyclerView: RecyclerView

        fun newInstance(cont: Context): ArtistFragment {
            context = cont
            return ArtistFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fragmentTransaction = fragmentManager?.beginTransaction()

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        this.adapter = ArtistAdapter(ArtistFragment.context) { id ->
            SongRepository.setId(id)
            fragmentTransaction
                ?.replace(
                    R.id.fragment_container,
                    SongFragment.newInstance(ArtistFragment.context)
                )
                ?.commit()
            fragmentTransaction?.addToBackStack(SongFragment.className)
        }

        recyclerView.adapter = this.adapter

        val presenter = ArtistPresenter(this)
        presenter.onRefresh()
    }

    class ArtistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.text_in_recyclerView
    }

    override fun showArtists(data: List<Artist>) {
        adapter.submitList(data)
    }
}

class ArtistAdapter(
    private val context: Context, val clickListener: (Int) -> (Unit)
) :
    ListAdapter<Artist, ArtistFragment.ArtistViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtistFragment.ArtistViewHolder {
        return ArtistFragment.ArtistViewHolder(
            LayoutInflater.from(context).inflate(R.layout.my_text_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArtistFragment.ArtistViewHolder, position: Int) {
        holder.textView.text = getItem(position)?.name ?: ""
        holder.textView.setOnClickListener {
            clickListener(getItem(position)?.id ?: 0)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem == newItem
        }

    }

}

interface ArtistView {
    fun showArtists(data: List<Artist>)
}