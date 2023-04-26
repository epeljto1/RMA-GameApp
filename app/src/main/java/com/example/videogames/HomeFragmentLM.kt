package com.example.videogames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragmentLM : Fragment() {
    private lateinit var Games: RecyclerView
    private lateinit var GamesAdapter: GameListAdapter
    private var GamesList =  GameData.getAll()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        var view = inflater.inflate(R.layout.fragment_home_lm, container, false)
        Games = view.findViewById(R.id.game_list)
        Games.layoutManager = GridLayoutManager(activity,1)
        GamesAdapter = GameListAdapter(arrayListOf()) {game ->
            val bundle = Bundle()
            bundle.putString("game_title_textview",game.title)
     }
        Games.adapter = GamesAdapter
        GamesAdapter.updateGames(GamesList)
        return view;
    }
}