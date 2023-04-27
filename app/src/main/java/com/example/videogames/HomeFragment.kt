package com.example.videogames


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeFragment : Fragment() {
    private lateinit var Games: RecyclerView
    private lateinit var GamesAdapter: GameListAdapter
    private var GamesList =  GameData.getAll()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        lateinit var view: View
        if(orientVal==1)
        view = inflater.inflate(R.layout.fragment_home, container, false)
        else if(orientVal==2){
        view = inflater.inflate(R.layout.fragment_home_lm, container, false)
            val bundle = Bundle()
            bundle.putString("game_title_textview",GameData.getAll()[0].title)
        findNavController().navigate(R.id.gameDetailsItem,bundle)}
        Games = view.findViewById(R.id.game_list)
        Games.layoutManager = GridLayoutManager(activity,1)
        GamesAdapter = GameListAdapter(arrayListOf()) {game ->
        val bundle = Bundle()
        bundle.putString("game_title_textview",game.title)
            if(orientVal==1)
        view.findNavController().navigate(R.id.action_homeItem_to_gameDetailsItem,bundle)
            if(orientVal==2)
                findNavController().navigate(R.id.gameDetailsItem,bundle)
        }
        Games.adapter = GamesAdapter
        GamesAdapter.updateGames(GamesList)
        return view;
    }

}