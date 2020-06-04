package ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import data.Song
import kotlinx.android.synthetic.main.song_element.view.*
import logic.SongPresenter

class SongFragment : Fragment(R.layout.fragment_layout), SongView {

    private lateinit var adapter: SongAdapter
    private lateinit var progress: ProgressBar

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
        activity?.setTitle(R.string.song_page)

        progress = view.findViewById(R.id.progress)

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
        val song_card: MaterialCardView = view.song_card
    }

    override fun showSongs(data: List<Song>) {
        adapter.submitList(data)
    }

    override fun showError(it: Throwable) {
        Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progress.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = ProgressBar.INVISIBLE
    }
}

class SongAdapter(private val context: Context, private val clickListener: (String) -> Unit) :
    ListAdapter<Song, SongFragment.MyViewHolder>(DiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongFragment.MyViewHolder {
        return SongFragment.MyViewHolder(
            (LayoutInflater.from(context).inflate(R.layout.song_element, parent, false))
        )
    }

    override fun onBindViewHolder(holder: SongFragment.MyViewHolder, position: Int) {
        Glide
            .with(context)
            .load(getItem(position)?.image_url)
            .into(holder.song_card.song_photo)
        holder.song_card.song_title.text = getItem(position)?.title
        holder.song_card.setOnClickListener { clickListener(getItem(position)?.url ?: "") }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }

    }

}

interface SongView {
    fun showSongs(data: List<Song>)
    fun showError(it: Throwable)
    fun showLoading()
    fun hideLoading()
}