package com.example.videogames

class GameData {
    companion object GameObject
    {
        fun getAll(): List<Game>
        {
            return listOf(
                Game("Tekken 6","PS3","27.10.2009",8.8),
                Game("Crash Nitro Kart","PS2","11.11.2003",7.4),
                Game("Tomb Raider: Underworld","Windows","18.11.2008",8.0),
                Game("Red Dead Redemption","Xbox 360","18.05.2010",9.7),
                Game("Spyro the Dragon","PS1","09.09.1998",9.0),
            )
        }
        fun getDetails(title:String): Game
        {
            val games: ArrayList<Game> = arrayListOf()
            games.addAll(getAll())
            val game = games.find{game -> title == game.title}
            return game?:Game("Test","Test","Test",0.0)
        }
    }
}