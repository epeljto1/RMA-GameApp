package com.example.videogames

class GameData {
    companion object GameObject
    {
        fun getAll(): List<Game>
        {
            return listOf(
                Game("Tekken 6","PS3","27.10.2009",8.8,
                    "","","","","","",listOf()),
                Game("Grand Theft Auto V","PS5","17.09.2013",10.0,
                "","","","","","", listOf()
                ),
                Game("Crash Nitro Kart","PS2","11.11.2003",7.4,
                "","","","","","", listOf()
                ),
                Game("Tomb Raider: Underworld","Windows","18.11.2008",8.0,
                "","","","","","", listOf()
                ),
                Game("Red Dead Redemption","Xbox 360","18.05.2010",9.7,
                "","","","","","", listOf()
                ),
                Game("Spyro the Dragon","PS1","09.09.1998",9.0,
                "","","","","","", listOf()
                ),
                Game("Sonic & Sega All-Stars Racing","Wii","23.02.2010",8.0,
                "","","","","","", listOf()
                ),
                Game("Tomb Raider: Anniversary","Windows","1.06.2007",7.0,
                "","","","","","", listOf()
                ),
                Game("Mortal Kombat","PS3","19.04.2011",8.0,
                "","","","","","", listOf()
                ),
                Game("Grand Theft Auto: San Andreas","Windows","26.10.2004",9.3,
                "","","","","","", listOf()
                )

            )
        }
        fun getDetails(title:String): Game
        {
            val games: ArrayList<Game> = arrayListOf()
            games.addAll(getAll())
            val game = games.find{game -> title == game.title}
            return game?:Game("Test","Test","Test",0.0,
                "Test","Test","Test","Test","Test","Test", listOf()
            )
        }
    }
}