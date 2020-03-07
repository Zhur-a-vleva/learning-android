package com.example.co

import android.os.Bundle
import androidx.fragment.app.Fragment

class RegistrationName : Fragment() {

    companion object {

        const val className = "RegistrationName"

        fun newInstance(savedInstanceState: Bundle?): RegistrationName {
            return RegistrationName()
        }
    }
}