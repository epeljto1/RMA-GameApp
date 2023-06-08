package ba.etf.rma23.projekat.data.repositories


class GameData {
    companion object GameObject
    {
        fun getAll(): List<Game>
        {

            return listOf(
                Game(1,"Tekken 6","PS3","27.10.2009",8.8,
                    "tekken6","T","Namco Bandai Games","Namco Bandai Games",
                    "Fighting","Tekken 6 (Japanese: 鉄拳6) is a fighting game developed and published by Bandai Namco Games. " +
                            "It is the sixth main and seventh installment in the Tekken franchise.",
                    listOf(
                        UserRating("emina",2304021557,5.0),
                        UserReview("faris",2303011345,"I love this game."),
                    UserReview("dalila",2303011023,"So many characters!"),
                    UserReview("amer",2301011841,"Great game to play with your friends!"),
                    UserRating("hana",2304011515,4.5)
                    )),
                Game(2,"Grand Theft Auto V","PS5","17.09.2013",10.0,
                "grandtheftautov","M","Rockstar North","Rockstar Games","Action-adventure",
                    "The single-player story follows three protagonists and " +
                            "their attempts to commit heists while under pressure from a corrupt government agency and powerful criminals.",
                    listOf(
                        UserRating("ines",2304021540,5.0),
                    UserReview("emina2",2204021415,"One of the best games ever"),
                    UserRating("samir1",2303151147,4.5),
                    UserReview("user23",2303151622,"I will never get bored of playing this!"),
                        UserRating("gaminchannel11",2304021601,5.0)
                    )
                ),
                Game(3,"Crash Nitro Kart","PS2","11.11.2003",7.4,
                "crashnitrokart","E","Vicarious Visions","Universal Unteractive","Racing",
                    "The game's story centers on the abduction of Crash Bandicoot, " +
                            "along with other characters, by the dictator Emperor Velo XXVII." +
                            "Threatening to destroy the Earth, he forces them to race.", listOf()
                ),
                Game(4,"Tomb Raider: Underworld","Windows","18.11.2008",8.0,
                "tombraiderunderworld","T","Crystal Dynamics","Eidos Interactive",
                    "Action-adventure",
                    "Underworld follows archaeologist-adventurer Lara Croft as she searches for Mjolnir, " +
                            "an artefact key to entering the realm of Helheim, while confronting adversaries from her past.", listOf()
                ),
                Game(5,"Red Dead Redemption","Xbox 360","18.05.2010",9.7,
                "reddeadredemption","M","Rockstar San Diego","Rockstar Games",
                    "Action-adventure","The game is set during the decline of the American frontier in the year 1911 and follows John Marston " +
                            "whose family is taken hostage in ransom for his services.", listOf()
                ),
                Game(6,"Spyro the Dragon","PS1","09.09.1998",9.0,
                "spyrothedragon","E","Insomniac Games","Sony Computer Entertainment","Platform",
                    "A young purple dragon named Spyro, " +
                            "and his dragonfly friend, Sparx, must journey across the Dragon Kingdom to defeat Gnasty Gnorc, " +
                            "who has overtaken the 5 dragon Homeworlds.", listOf()
                ),
                Game(7,"Sonic & Sega All-Stars Racing","Wii","23.02.2010",8.0,
                "sonicandsegaallstarsracing","E","Sumo Digital","Sega",
                    "Racing","The game is a mascot kart racing game. Players can choose to race as one of 20 characters from " +
                            "various Sega franchises such as Sonic the Hedgehog, Crazy Taxi, and Fantasy Zone.", listOf()
                ),
                Game(8,"Tomb Raider: Anniversary","Windows","01.06.2007",7.0,
                "tombraideranniversary","T","Crystal Dynamics","Eidos Interactive",
                    "Action-adventure","Taking place before the events of 2006's Tomb Raider: Legend, Anniversary " +
                            "follows series protagonist Lara Croft's quest for the Scion of Atlantis.", listOf()
                ),
                Game(9,"Mortal Kombat","PS3","19.04.2011",8.0,
                "mortalkombat","M","NetherRealm Studios","Warner Bros. Interactive Entertainment","Fighting",
                    "The divine protector of Earth, Raiden, attempts to change the aftermath of the events of " +
                            "Armageddon by contacting his past self as he faces defeat at the hands of the evil emperor of Outworld, Shao Kahn.", listOf()
                ),
                Game(10,"Grand Theft Auto: San Andreas","Windows","26.10.2004",9.3,
                "grandtheftautosanandreas","A","Rockstar North","Rockstar Games","Action-adventure",
                    "The story follows Carl \"CJ\" Johnson, who returns home following his mother's murder and is drawn back into " +
                            "a life of crime while clashing with corrupt authorities and powerful criminals.", listOf()
                )

            )
        }
        fun getDetails(title:String): Game
        {
            val games: ArrayList<Game> = arrayListOf()
            games.addAll(getAll())
            val game = games.find{game -> title == game.title}
            return game ?: Game(0,"","","",0.0,
                "","","","","","",listOf()
            )
        }
    }
}