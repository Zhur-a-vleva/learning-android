package com.example.cleverplaner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class List : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    companion object {

        const val className = "list_fragment"
        const val TASK_LIST_KEY = "taskList"

        fun newInstance(): List {
            return List()
        }
    }

    class Adapter(private val taskList: ArrayList<String>?) :
        RecyclerView.Adapter<Adapter.ViewHolder>() {

        class ViewHolder(singleTask: TextView) : RecyclerView.ViewHolder(singleTask) {
            val task = singleTask
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.task_view, parent, false) as TextView
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.task.text = taskList?.get(position) ?: ""
        }

        override fun getItemCount(): Int {
            return taskList?.size ?: 0
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.list, container, false)

        if(savedInstanceState != null) {
            var taskList = savedInstanceState.getStringArrayList(TASK_LIST_KEY)
            recyclerView = view.findViewById(R.id.list_recycler_view)
            layoutManager = LinearLayoutManager(context)
            recyclerView.layoutManager = layoutManager
            viewAdapter = Adapter(taskList)
        }

        return view
    }

}