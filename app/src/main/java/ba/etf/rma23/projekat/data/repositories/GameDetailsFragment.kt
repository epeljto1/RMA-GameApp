package ba.etf.rma23.projekat.data.repositories

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma23.projekat.MainActivity
import ba.etf.rma23.projekat.R
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GameDetailsFragment : Fragment(){
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
    private lateinit var saveButton: Button
    private lateinit var removeButton: Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        lateinit var view: View
        if(orientVal ==1)
        view = inflater.inflate(R.layout.fragment_game_details, container, false)
        else if(orientVal ==2)
            view = inflater.inflate(R.layout.fragment_game_details_lm, container, false)
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
        saveButton = view.findViewById(R.id.save_button)
        removeButton = view.findViewById(R.id.remove_button)

        saveButton.setOnClickListener{
            saveGame(theGame)
        }

        removeButton.setOnClickListener{
            removeGame(theGame.id)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        populateDetails(theGame)
    }

    override fun onResume() {
        super.onResume()
        if(orientVal ==1) {
            val main = activity as MainActivity
            main.enableBottomNav()
        }
    }

    private fun populateDetails(game : Game) {
        title.text = game.title
        val ImpressionList: List<UserImpression> = game.userImpressions ?: emptyList()
        val sortedList=ImpressionList.sortedBy { it.timestamp }
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
        val url : String? = game.coverImage
        Glide.with(requireContext())
            .load("https:$url")
            .into(coverImage)
    }

    fun saveGame(game:Game) {
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            try {
                AccountGamesRepository.saveGame(game)
            } catch(e: Exception) {
                onError()
            }
        }
    }

    fun removeGame(id:Int) {
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            try {
                AccountGamesRepository.removeGame(id)
            } catch(e: Exception) {
                onError()
            }
        }
    }

    fun onError() {
        val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }

}