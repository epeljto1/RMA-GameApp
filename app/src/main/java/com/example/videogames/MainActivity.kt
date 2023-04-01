package com.example.videogames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

public var click: Int = 0


class MainActivity : AppCompatActivity() {
    private lateinit var Games: RecyclerView
    private lateinit var GamesAdapter: GameListAdapter
    private var GamesList =  GameData.getAll()
    private lateinit var homeBtn: Button
    private lateinit var detailsBtn: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeBtn = findViewById(R.id.home_button)
        homeBtn.isEnabled = false
        detailsBtn = findViewById(R.id.details_button)
        if(click == 0)
        detailsBtn.isEnabled = false
        else if(click == 1) {
            detailsBtn.isEnabled = true
            detailsBtn.setOnClickListener {
                val intent = Intent(this, GameDetailActivity::class.java).apply {
                    putExtra("game_title_textview", igrica)
                }
                startActivity(intent)
            }
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