package com.example.geniusapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class HomePage : AppCompatActivity() {

    private val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fragmentTransaction
            .add(R.id.fragment_container, ArtistsFragment.newInstance( this, fragmentTransaction))
            .commit()
        fragmentTransaction.addToBackStack(ArtistsFragment.className)

    }

}