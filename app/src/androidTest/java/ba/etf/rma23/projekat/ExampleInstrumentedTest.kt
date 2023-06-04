package ba.etf.rma23.projekat

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.PositionAssertions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ba.etf.rma23.projekat.data.repositories.GameData
import ba.etf.rma23.projekat.data.repositories.GameListAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OwnEspressoTests {
    @get:Rule
    var homeRule:ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    // Prvi scenarij Test1() provjerava ispravnost aplikacije kada se ona tek pokrene
    // Testira se da li je Home fragment vidljiv i da li aplikacija ima bottom navigation
    // Takodjer se testira da li taj bottom navigation ima dvije opcije - Home i Details
    // i da li je bottom navigation disabled jer jos nije kliknuto na neku igricu
    // Sve sto se testira i kako test provjerava navedeno se nalazi unutar njega uz komentare
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

    // Drugi scenarij Test2() provjerava ispravnost aplikacije nakon klika na neku igricu
    // Testira se da li je Game Details fragment vidljiv i da li su prikazani detalji igrice na koju je kliknuto
    // Provjerava se da li je bottom navigation sada enabled i da li se klikom na Home opciju vraca na Home fragment
    // Zatim se provjerava klik na opciju Details i da li se ponovo otvaraju detalji igrice na koju je posljednje kliknuto
    // Sve sto se testira i kako test provjerava navedeno se nalazi unutar njega uz komentare
    @Test
    fun Test2(){

        // Provjera da li se nakon klika na igricu otvaraju detalji o igrici
        var igra = GameData.getAll()[1]
        onView(withId(R.id.game_list)).perform(RecyclerViewActions.actionOnItem<GameListAdapter.ViewHolder>(allOf(
            hasDescendant(withText(igra.title)),
            hasDescendant(withText(igra.releaseDate)),
            hasDescendant(withText(igra.rating.toString()))
        ),click()))

        onView(withId(R.id.gameDetailsItem)).check(matches(isDisplayed()))
        onView(withId(R.id.game_title_textview)).check(matches(isDisplayed()))

        // Provjera da li su se otvorili ispravni detalji
        var title1 = onView(withId(R.id.game_title_textview))
        title1.check(matches(withText(igra.title)))
        onView(withId(R.id.cover_imageview)).check(matches(isDisplayed()))
        onView(withId(R.id.description_textview)).check(matches(isDisplayed()))

        // Provjera da li je traka za navigaciju enabled nakon klika na igricu
        onView(withId(R.id.homeItem)).check(matches(isEnabled()))

        // Provjera da li nakon klika na opciju Home se prikaze Home fragment
        onView(withId(R.id.homeItem)).perform(click())
        onView(withId(R.id.homeItem)).check(matches(isDisplayed()))

        // Provjera da li nakon klika na opciju Details se prikaze ponovo Details fragment
        onView(withId(R.id.gameDetailsItem)).perform(click())
        onView(withId(R.id.gameDetailsItem)).check(matches(isDisplayed()))

        // Provjera da li su se ponovo otvorili ispravni detalji
        var title2 = onView(withId(R.id.game_title_textview))
        title2.check(matches(withText(igra.title)))

    }


}

