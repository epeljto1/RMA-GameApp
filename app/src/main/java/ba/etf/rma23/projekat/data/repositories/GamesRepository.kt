package ba.etf.rma23.projekat.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GamesRepository {

    var games : List<Game>? = emptyList()

    suspend fun getGamesByName(name:String):List<Game>
    {
        return withContext(Dispatchers.IO)
        {
            var response = IGDBApiConfig.retrofit.getGamesByName(name)
            val responseBody = response.body()
            if (responseBody != null) {
                for(game in responseBody) {
                    game.releaseDate = game.releaseDates?.get(0)?.human
                    if(game.ageRatings != null) {
                        for (rating in game.ageRatings!!) {
                            if (rating.category == 1 && rating.rating == 6) {
                                game.esrbRating = "RP"
                                break
                            } else if (rating.category == 1 && rating.rating == 7) {
                                game.esrbRating = "EC"
                                break
                            } else if (rating.category == 1 && rating.rating == 8) {
                                game.esrbRating = "E"
                                break
                            } else if (rating.category == 1 && rating.rating == 9) {
                                game.esrbRating = "E10+"
                                break
                            } else if (rating.category == 1 && rating.rating == 10) {
                                game.esrbRating = "T"
                                break
                            } else if (rating.category == 1 && rating.rating == 11) {
                                game.esrbRating = "M"
                                break
                            } else if (rating.category == 1 && rating.rating == 12) {
                                game.esrbRating = "AO"
                                break
                            }
                        }
                    }
                    game.platform = game.platforms?.get(0)?.abbreviation
                    game.genre = game.genres?.get(0)?.name
                    game.coverImage = game.cover?.url
                }
            }
            games = responseBody

            responseBody ?: emptyList()
        }
    }

    suspend fun getGamesSafe(name:String):List<Game>
    {
        val games = getGamesByName(name)
        val age = AccountGamesRepository.getAge()
        var filteredGames: ArrayList<Game> = arrayListOf()

        if(age == null) return emptyList()

            for(game in games)
            {
                if(age>=18) filteredGames.add(game)
                else if(age<10) if(game.esrbRating=="E") filteredGames.add(game)
                else if(age <13) if(game.esrbRating=="E" || game.esrbRating=="E10") filteredGames.add(game)
                else if(age<17) if(game.esrbRating=="E" || game.esrbRating=="E10" || game.esrbRating=="T") filteredGames.add(game)
                else if(age <18) if(game.esrbRating=="E" || game.esrbRating=="E10" || game.esrbRating=="T" || game.esrbRating =="M") filteredGames.add(game)
            }
        return filteredGames


    }

    suspend fun sortGames():List<Game>
    {
        val savedGames = AccountGamesRepository.getSavedGames()
        var saved : ArrayList<Game> = arrayListOf()
        var other : ArrayList<Game> = arrayListOf()

        for(game in games!!)
        {
            if(game in savedGames)
                    saved.add(game)
                else
                    other.add(game)
        }
        val final = (saved+other) as ArrayList<Game>
        return final
    }

    suspend fun getGame(name:String):List<Game>
    {
        return withContext(Dispatchers.IO)
        {
            var response = IGDBApiConfig.retrofit.getGamesByName(name)
            val responseBody = response.body()
            if (responseBody != null) {
                for(game in responseBody) {
                    game.releaseDate = game.releaseDates?.get(0)?.human
                    if(game.ageRatings != null) {
                        for (rating in game.ageRatings!!) {
                            if (rating.category == 1 && rating.rating == 6) {
                                game.esrbRating = "RP"
                                break
                            } else if (rating.category == 1 && rating.rating == 7) {
                                game.esrbRating = "EC"
                                break
                            } else if (rating.category == 1 && rating.rating == 8) {
                                game.esrbRating = "E"
                                break
                            } else if (rating.category == 1 && rating.rating == 9) {
                                game.esrbRating = "E10+"
                                break
                            } else if (rating.category == 1 && rating.rating == 10) {
                                game.esrbRating = "T"
                                break
                            } else if (rating.category == 1 && rating.rating == 11) {
                                game.esrbRating = "M"
                                break
                            } else if (rating.category == 1 && rating.rating == 12) {
                                game.esrbRating = "AO"
                                break
                            }
                        }
                    }
                    game.platform = game.platforms?.get(0)?.abbreviation
                    game.genre = game.genres?.get(0)?.name
                    game.coverImage = game.cover?.url
                }
            }

            responseBody ?: emptyList()
        }
    }

}