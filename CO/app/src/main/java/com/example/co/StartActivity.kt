package com.example.co

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {

    private val fragmentTransaction = supportFragmentManager.beginTransaction()
    private lateinit var loginPage: LoginPage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)

        loginPage = LoginPage.newInstance(savedInstanceState)

        //set login page into fragment container
        fragmentTransaction
            .add(R.id.fragment_container, loginPage)
            .commit()
        fragmentTransaction.addToBackStack(LoginPage.className)
    }

    //removing first fragment from container and calling super.onBackPressed()
    override fun onBackPressed() {
        fragmentTransaction.remove(loginPage)
        super.onBackPressed()
    }
}
