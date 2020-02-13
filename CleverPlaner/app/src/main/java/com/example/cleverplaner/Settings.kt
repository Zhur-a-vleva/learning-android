package com.example.cleverplaner

import androidx.fragment.app.Fragment

class Settings : Fragment() {

    companion object {
        const val className = "settings_fragment"

        fun newInstance() : Settings {
            return Settings()
        }
    }

}