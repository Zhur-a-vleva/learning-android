package com.example.co

import android.os.Bundle
import androidx.fragment.app.Fragment

class Login : Fragment() {

    companion object {

        const val className = "Login"

        fun newInstance(savedInstanceState : Bundle?): Login {
            return Login()
        }

    }

}