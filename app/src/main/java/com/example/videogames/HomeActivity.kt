package com.example.videogames

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

            orientVal=1
            setContentView(R.layout.activity_main)


            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            val navView: BottomNavigationView = findViewById(R.id.bottom_nav)


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
        } else {
            orientVal=2
            setContentView(R.layout.activity_main_lm)
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.detailsContainer) as NavHostFragment
        }
    }
    fun enableBottomNav()
    {
        findViewById<BottomNavigationView>(R.id.bottom_nav).menu.getItem(0).isEnabled = true
        findViewById<BottomNavigationView>(R.id.bottom_nav).menu.getItem(1).isEnabled = true
    }
}