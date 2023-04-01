package com.example.videogames

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class GameDetailActivity : AppCompatActivity() {
    private lateinit var game: Game
    private lateinit var title: TextView
    private lateinit var coverImage: ImageView
    private lateinit var platform: TextView
    private lateinit var releaseDate: TextView
    private lateinit var esrbrating: TextView
    private lateinit var developer: TextView
    private lateinit var publisher: TextView
    private lateinit var genre: TextView
    private lateinit var description: TextView
    private lateinit var Impressions: RecyclerView
    private lateinit var ImpressionAdapter: GameImpressionAdapter
    private lateinit var detailsBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_details_layout)

        detailsBtn = findViewById(R.id.details_button)
        detailsBtn.isEnabled=false

        val buttonClick = findViewById<Button>(R.id.home_button)
        buttonClick.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            click = 1
            startActivity(intent)

        }
        title = findViewById(R.id.game_title_textview)
        coverImage = findViewById(R.id.cover_imageview)
        platform = findViewById(R.id.platforn_textview)
        releaseDate = findViewById(R.id.release_date_textview)
        esrbrating = findViewById(R.id.esrb_rating_textview)
        developer = findViewById(R.id.developer_textview)
        publisher = findViewById(R.id.publisher_textview)
        genre = findViewById(R.id.genre_textview)
        description = findViewById(R.id.description_textview)
        Impressions = findViewById(R.id.impression_list)


        val extras = intent.extras
        if (extras != null) {
            game = GameData.getDetails(extras.getString("game_title_textview",""))
            populateDetails()
        } else {
            finish()
        }
    }

    private fun populateDetails() {
        title.text = game.title
        val context: Context = coverImage.context
        var g = GameData.getDetails(game.title)
        val imageName: String = g.coverImage
        var id: Int = context.resources.getIdentifier(imageName,"drawable",context.packageName)
        val ImpressionList: List<UserImpression> = g.userImpressions
        val sortedList=ImpressionList.sortedBy { it.timestamp }
        coverImage.setImageResource(id)
        platform.text = game.platform
        releaseDate.text = game.releaseDate
        esrbrating.text = game.esrbRating
        developer.text = game.developer
        publisher.text = game.publisher
        genre.text = game.genre
        description.text = game.description
        Impressions.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        ImpressionAdapter = GameImpressionAdapter(listOf())
        Impressions.adapter=ImpressionAdapter
        ImpressionAdapter.updateImpressions(sortedList.asReversed())

    }


}