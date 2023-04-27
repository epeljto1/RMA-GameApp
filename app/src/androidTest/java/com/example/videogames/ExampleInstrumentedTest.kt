package com.example.videogames


import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.PositionAssertions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestLayout {


    @get:Rule
    var homeRule:ActivityScenarioRule<HomeActivity> = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun Test1(){

        // Provjera da li je otvoren Home fragment kada se pokrene aplikacija
        val homeFragment = onView(withId(R.id.homeItem))
        homeFragment.check(matches(isDisplayed()))

        // Provjera da li aplikacija sadrzi traku za navigaciju
        val bottomNav = onView(withId(R.id.bottom_nav))
        bottomNav.check(matches(isDisplayed()))

        // Provjera da li traka za navigaciju ima dvije opcije
        bottomNav.check{view , _ ->
            assertTrue(view is BottomNavigationView)
            assertEquals(2,(view as BottomNavigationView).menu.size())}

        // Provjera da li je traka za navigaciju disabled kada se pokrene aplikacija
        onView(withId(R.id.homeItem)).check(matches(not(isEnabled())))
        onView(withId(R.id.gameDetailsItem)).check(matches(not(isEnabled())))
    }

    fun Test2(){

    }



}

