package com.example.videogames

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GameDetailsFragmentLM : Fragment() {
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_game_details_lm, container, false)
        title = view.findViewById(R.id.game_title_textview)
        coverImage = view.findViewById(R.id.cover_imageview)
        platform = view.findViewById(R.id.platform_textview)
        releaseDate = view.findViewById(R.id.release_date_textview)
        esrbrating = view.findViewById(R.id.esrb_rating_textview)
        developer = view.findViewById(R.id.developer_textview)
        publisher = view.findViewById(R.id.publisher_textview)
        genre = view.findViewById(R.id.genre_textview)
        description = view.findViewById(R.id.description_textview)
        Impressions = view.findViewById(R.id.impression_list)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        game=GameData.getAll()[0]
        populateDetails()
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
        Impressions.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        ImpressionAdapter = GameImpressionAdapter(listOf())
        Impressions.adapter=ImpressionAdapter
        ImpressionAdapter.updateImpressions(sortedList.asReversed())
    }
}
