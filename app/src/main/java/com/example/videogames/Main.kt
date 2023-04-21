package com.example.videogames

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        loadFragment(HomeFragment())
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
        navView.setupWithNavController(navController)
            navView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.homeItem -> {
                        loadFragment(HomeFragment())
                        true
                    }
                    R.id.gameDetailsItem -> {
                        val bundle = Bundle()
                        bundle.putString("game_title_textview", igrica)
                        val fragment = GameDetailsFragment()
                        fragment.arguments = bundle
                        loadFragment(fragment)
                        true
                    }
                }
                false
            }
    }

    private fun loadFragment(fragment: Fragment)
    {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment,fragment)
        transaction.commit()
    }

}