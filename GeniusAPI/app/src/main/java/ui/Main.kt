package ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class Main : AppCompatActivity() {

    private val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    private val artistsFragment =
        ArtistFragment.newInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fragmentTransaction
            .add(R.id.fragment_container, artistsFragment)
            .commit()
        fragmentTransaction.addToBackStack(ArtistFragment.className)

    }

    override fun onBackPressed() {
        fragmentTransaction.remove(artistsFragment)
        super.onBackPressed()
    }

}