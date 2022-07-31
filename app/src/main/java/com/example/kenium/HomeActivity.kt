package com.example.kenium

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView
    private lateinit var currentFragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,HomeFragment()).commit()

        bottomNav = findViewById(R.id.bottomNavigation)
        bottomNav.setOnNavigationItemSelectedListener(navlistener)



    }



    val navlistener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.Home -> {
                currentFragment = HomeFragment()
            }
            R.id.Search -> {
                currentFragment = SearchFragment()
            }
            R.id.Profile -> {
                currentFragment = ProfileFragment()
            }
            R.id.Downloads -> {
                currentFragment = DownloadsFragment()
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,currentFragment).commit()
        true
    }
}