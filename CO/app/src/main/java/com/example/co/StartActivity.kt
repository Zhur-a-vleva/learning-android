package com.example.co

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

class StartActivity : AppCompatActivity() {

    private val FIRST_START_KEY = "FIRST_START_KEY"
    private val fragmentTransaction = supportFragmentManager.beginTransaction()
    private lateinit var login: Login
    private lateinit var registrationName: RegistrationName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)

        var sharedPreferences = getPreferences(MODE_PRIVATE)
        when (sharedPreferences.contains(FIRST_START_KEY)) {
            true -> {
                login = Login.newInstance(savedInstanceState)

                //set login page into fragment container
                fragmentTransaction
                    .add(R.id.fragment_container, login)
                    .commit()
                fragmentTransaction.addToBackStack(Login.className)
            }
            else -> {
                registrationName = RegistrationName.newInstance(savedInstanceState)

                //set registrationName page into fragment container
                fragmentTransaction
                    .add(R.id.fragment_container, registrationName)
                    .commit()
                fragmentTransaction.addToBackStack(RegistrationName.className)

                //add flag
                sharedPreferences.edit { putBoolean(FIRST_START_KEY, false) }
            }
        }

    }

    //removing first fragment from container and calling super.onBackPressed()
    override fun onBackPressed() {
        fragmentTransaction.remove(login)
        fragmentTransaction.remove(registrationName)
        super.onBackPressed()
    }
}
