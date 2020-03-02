package com.example.co

import android.os.Bundle
import androidx.fragment.app.Fragment

class LoginPage : Fragment() {

    companion object {

        const val className = "LoginPage"

        fun newInstance(savedInstanceState : Bundle?): LoginPage {
            return LoginPage()
        }

    }

}