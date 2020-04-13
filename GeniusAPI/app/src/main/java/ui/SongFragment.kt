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
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import data.Song
import kotlinx.android.synthetic.main.my_text_view.view.*
import logic.SongPresenter

class SongFragment : Fragment(R.layout.fragment_layout), SongView {

    private lateinit var adapter: SongAdapter

    companion object {

        const val className = "SongView"
        private lateinit var context: Context
        private lateinit var recyclerView: RecyclerView

        fun newInstance(
            cont: Context
        ): SongFragment {
            context = cont
            return SongFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        this.adapter = SongAdapter(SongFragment.context) { url ->
            val browseIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browseIntent)
        }
        recyclerView.adapter = this.adapter

        val presenter = SongPresenter(this)
        presenter.onRefresh()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.text_in_recyclerView
    }

    override fun showSongs(data: List<Song>) {
        adapter.submitData(data)
    }
}

class SongAdapter(private val context: Context, private val clickListener: (String) -> Unit) :
    ListAdapter<Song, SongFragment.MyViewHolder>(DiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongFragment.MyViewHolder {
        return SongFragment.MyViewHolder(
            (LayoutInflater.from(context).inflate(R.layout.my_text_view, parent, false))
        )
    }

    override fun onBindViewHolder(holder: SongFragment.MyViewHolder, position: Int) {
        holder.textView.text = getItem(position)?.title
        holder.textView.setOnClickListener { clickListener(getItem(position)?.url ?: "") }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }

    }

    fun submitData(data: List<Song>) {
        submitList(data)
        notifyDataSetChanged()
    }
}

interface SongView {
    fun showSongs(data: List<Song>)
}