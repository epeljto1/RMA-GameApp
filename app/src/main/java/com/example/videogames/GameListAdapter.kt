package com.example.videogames

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameListAdapter(
    private var games: List<Game>
) : RecyclerView.Adapter<GameListAdapter.ViewHolder>() {

  class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
      val game_rating_textview: TextView
      val game_title_textview: TextView
      val game_release_date_textview: TextView
      val game_platform_textview: TextView
      init {
          game_rating_textview=view.findViewById(R.id.game_rating_textview)
          game_title_textview=view.findViewById(R.id.game_title_textview)
          game_release_date_textview=view.findViewById(R.id.game_release_date_textview)
          game_platform_textview=view.findViewById(R.id.game_platform_textview)
      }
  }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.game_list_layout,viewGroup,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.game_platform_textview.text=games[position].platform
        viewHolder.game_release_date_textview.text=games[position].releaseDate
        viewHolder.game_title_textview.text=games[position].title
        viewHolder.game_rating_textview.text=games[position].rating.toString()

    }
    override fun getItemCount():Int = games.size

    fun updateGames(games: List<Game>) {
        this.games = games
        notifyDataSetChanged()
        }
}