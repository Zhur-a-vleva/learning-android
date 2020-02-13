package com.example.cleverplaner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        //setting the start fragment
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        if (fragmentTransaction.isEmpty) {
            fragmentTransaction
                .add(R.id.fragment_container, Tasks.newInstance())
                .commit()
            fragmentTransaction.addToBackStack(Tasks.className)
        }

        //setting navigation on bottomNavigation
        bottomNavigationView.setOnNavigationItemSelectedListener {
            // FIXME: переделать на when(it.id) { }
            if (it.itemId == R.id.bottom_navigation_list) {
                fragmentTransaction
                    .add(R.id.fragment_container, List.newInstance())
                    .commit()
                fragmentTransaction.addToBackStack(List.className)
                true
            } else if (it.itemId == R.id.bottom_navigation_tasks) {
                fragmentTransaction
                    .add(R.id.fragment_container, Tasks.newInstance())
                    .commit()
                fragmentTransaction.addToBackStack(Tasks.className)
                // TODO: поэкспериминтировать, что будет, если `false`
                false
            } else {
                fragmentTransaction
                    .add(R.id.fragment_container, Settings.newInstance())
                    .commit()
                fragmentTransaction.addToBackStack(Settings.className)
                true
            }
        }
    }
}

