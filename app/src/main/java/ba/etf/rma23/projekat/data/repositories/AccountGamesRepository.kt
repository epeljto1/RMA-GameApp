package ba.etf.rma23.projekat.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AccountGamesRepository {
    var acHash : String = "a513b8c3-f994-41ea-aacf-f47e1f3efa81"
    private var age : Int? = null
    var savedGames : ArrayList<Game> = ArrayList()

    fun setHash(acHash:String):Boolean
    {
        val k = this.acHash != null
        this.acHash = acHash
        return k
    }

    fun getHash():String
    {
        return acHash
    }

    suspend fun getSavedGames():List<Game>
    {
        val hash : String = getHash()
        return withContext(Dispatchers.IO)
        {
            var response = AccountApiConfig.retrofit.getSavedGames(hash)
            val responseBody = response.body()
            var games: ArrayList<Game> = ArrayList()
            if (responseBody != null) {
                for(game in responseBody) {
                    val igrice: List<Game> = GamesRepository.getGame(game.title)
                    val matchingGames = igrice.filter { it.id == game.igdbid }
                    games.addAll(matchingGames)
                }
            }
            savedGames = games
            games
        }
    }

    suspend fun saveGame (game:Game):Game
    {
        val hash : String = getHash()
        game.igdbid = game.id
        val request = SaveGameRequest(game)
        return withContext(Dispatchers.IO)
        {
            if(game !in savedGames) {
                var response = AccountApiConfig.retrofit.saveGame(hash, request)
                val responseBody = response.body()
                savedGames.add(game)
                responseBody ?: game
            }
            Game(0,"","","",0.0,
                "","","","","","",listOf())
        }
    }

    suspend fun removeGame (id:Int) : Boolean
    {
        val hash : String = getHash()
        var deleteGame: Game =  Game(0,"","","",0.0,
            "","","","","","",listOf())
        for(game in savedGames)
        {
            if(game.id == id) {
                deleteGame = game
                break }

        }
        return withContext(Dispatchers.IO)
        {
            try {
                var response = AccountApiConfig.retrofit.removeGame(hash, id)
                val responseBody = response.body()
                savedGames.remove(deleteGame)
                responseBody ?: Unit
                true
            }
            catch(e: java.lang.Exception)
            {
                false
            }
        }
    }

    suspend fun removeNonSafe():Boolean
    {
        val hash : String = getHash()
        val age : Int? = getAge()
        val games: List<Game> = getSavedGames()

        if(age==null)
            return false

            try {
                val gamesToDelete:ArrayList<Game> = ArrayList()
                for (game in games) {
                    if (age < 10 && game.esrbRating != "E") gamesToDelete.add(game)
                    else if (age < 13 && (game.esrbRating == "T" || game.esrbRating == "M" || game.esrbRating == "AO"))
                        gamesToDelete.add(game)
                    else if (age < 17 && (game.esrbRating == "M" || game.esrbRating == "AO"))
                        gamesToDelete.add(game)
                    else if (age < 18 && game.esrbRating == "AO")
                        gamesToDelete.add(game)
                }
                for(game in gamesToDelete)
                    removeGame(game.id)
            }
            catch (e:java.lang.Exception)
            {
                return false
            }
        return true
    }


    suspend fun getGamesContainingString(query: String): List<Game> {
        val hash : String = getHash()
        return withContext(Dispatchers.IO)
        {
            var response = AccountApiConfig.retrofit.getSavedGames(hash)
            val responseBody = response.body()
            responseBody ?: emptyList()
            responseBody?.filter { game -> game.title.contains(query, ignoreCase = true) } ?: emptyList()
        }
    }

    fun setAge(age : Int) : Boolean
    {
        if(age in 3..100)
        {
            this.age = age
            return true
        }
        return false
    }

    fun getAge() : Int?
    {
        return age;
    }
}