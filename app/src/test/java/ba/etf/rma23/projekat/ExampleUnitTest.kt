package ba.etf.rma23.projekat

import ba.etf.rma23.projekat.data.repositories.AccountGamesRepository
import ba.etf.rma23.projekat.data.repositories.Game
import ba.etf.rma23.projekat.data.repositories.GamesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() = runBlocking {
        /*GamesRepository.getGamesByName("tomb raider")
        var games = GamesRepository.sortGames()
        /*var games = Game(0,0,"","",listOf(),"",0.0,
            "","","","","","",listOf()
        )*/
        assertEquals("Tomb Raider: Legend",games[0].title)*/

        GamesRepository.getGamesByName("Tomb Raider")
        var games = GamesRepository.sortGames()
        assertEquals("Tomb Raider: Chronicles",games[0].title)
    }
}