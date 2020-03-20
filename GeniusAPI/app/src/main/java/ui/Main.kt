package ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class Main : AppCompatActivity() {

    private val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    private val artistsFragment =
        ArtistView.newInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fragmentTransaction
            .add(R.id.fragment_container, artistsFragment)
            .commit()
        fragmentTransaction.addToBackStack(ArtistView.className)

    }

    override fun onBackPressed() {
        fragmentTransaction.remove(artistsFragment)
        super.onBackPressed()
    }

}