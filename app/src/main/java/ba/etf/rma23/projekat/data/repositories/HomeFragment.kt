package ba.etf.rma23.projekat.data.repositories


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma23.projekat.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var Games: RecyclerView
    private lateinit var GamesAdapter: GameListAdapter
    private var GamesList = GameData.getAll()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        lateinit var view: View
        if(orientVal ==1)
        view = inflater.inflate(R.layout.fragment_home, container, false)
        else if(orientVal ==2){
        view = inflater.inflate(R.layout.fragment_home_lm, container, false)
            val bundle = Bundle()
            bundle.putString("game_title_textview", GameData.getAll()[0].title)
        findNavController().navigate(R.id.gameDetailsItem,bundle)}
        Games = view.findViewById(R.id.game_list)
        Games.layoutManager = GridLayoutManager(activity,1)
        GamesAdapter = GameListAdapter(arrayListOf()) {game ->
        val bundle = Bundle()
        bundle.putString("game_title_textview",game.title)
            if(orientVal ==1)
        view.findNavController().navigate(R.id.action_homeItem_to_gameDetailsItem,bundle)
            if(orientVal ==2)
                findNavController().navigate(R.id.gameDetailsItem,bundle)
        }
        Games.adapter = GamesAdapter
        /* getSavedGames() */
        val searchEditText: EditText = view.findViewById(R.id.search_query_edittext)
        val searchButton = view.findViewById<Button>(R.id.search_button) /*
        searchButton.setOnClickListener {
            val userInput = searchEditText.text.toString()
            getGamesByName(userInput)
        } */
        return view;
    }


    fun getGamesByName(name: String) {
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            try {
                GamesRepository.getGamesByName(name)
                val result = GamesRepository.sortGames()
                onSuccess(result)
            } catch (e: Exception) {
                onError()
            }
        }
    }

    fun getSavedGames(){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            try {
                val result = AccountGamesRepository.getSavedGames()
                onSuccess(result)
            } catch (e: Exception) {
                onError()
            }
        }
    }

    fun getGamesContainingString(query:String){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            try {
                val result = AccountGamesRepository.getGamesContainingString(query)
                onSuccess(result)
            } catch (e: Exception) {
                onError()
            }
        }
    }


    fun onSuccess(games:List<Game>){
        GamesAdapter.updateGames(games)
    }
    fun onError() {
        val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }

}