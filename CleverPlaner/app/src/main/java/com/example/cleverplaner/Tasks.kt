package com.example.cleverplaner

import androidx.fragment.app.Fragment

class Tasks : Fragment() {

    companion object {
        const val className = "tasks_fragment"

        fun newInstance() : Tasks {
            return Tasks()
        }
    }
}