package com.example.videogames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var Games: RecyclerView
    private lateinit var GamesAdapter: GameListAdapter
    private var GamesList =  GameData.getAll()
    private lateinit var homeBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeBtn = findViewById(R.id.home_button)
        homeBtn.isEnabled = false

        val buttonClick = findViewById<Button>(R.id.details_button)
        buttonClick.setOnClickListener {

        }
        Games = findViewById(R.id.game_list)
        Games.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        GamesAdapter = GameListAdapter(arrayListOf()) {game ->  showGameDetails(game)}
        Games.adapter = GamesAdapter
        GamesAdapter.updateGames(GamesList)
    }

    private fun showGameDetails(game: Game) {
        val intent = Intent(this, GameDetailActivity::class.java).apply {
            putExtra("game_title_textview", game.title)
        }
        startActivity(intent)
    }


}