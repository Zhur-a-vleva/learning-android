package com.example.geniusapi

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val data: Array<Song>, private val context: Context) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).
            inflate(R.layout.my_text_view, parent, false) as TextView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = data[position].title
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

class MyViewHolder(view: TextView) : RecyclerView.ViewHolder(view) {
    val textView = view
}