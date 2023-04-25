package com.example.videogames

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navView = findViewById(R.id.bottom_nav)


        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeItem -> {
                    navController.navigate(R.id.homeItem)
                    true
                }
                R.id.gameDetailsItem -> {
                    val bundle = Bundle()
                    bundle.putString("game_title_textview", igrica)
                    navController.navigate(R.id.gameDetailsItem, bundle)
                    true
                }
            }
            false
        }
        navView.menu.getItem(0).isEnabled = false
        navView.menu.getItem(1).isEnabled = false

    }

}