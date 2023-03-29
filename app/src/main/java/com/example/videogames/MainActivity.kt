package com.example.videogames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var Games: RecyclerView
    private lateinit var GamesAdapter: GameListAdapter
    private var GamesList =  GameData.getAll()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Games = findViewById(R.id.game_list)
        Games.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        GamesAdapter = GameListAdapter(listOf())
        Games.adapter = GamesAdapter
        GamesAdapter.updateGames(GamesList)
    }
}