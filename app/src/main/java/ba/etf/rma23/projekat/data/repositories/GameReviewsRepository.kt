package ba.etf.rma23.projekat.data.repositories

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GameReviewsRepository {
    var acHash : String = AccountGamesRepository.getHash()

    suspend fun getOfflineReviews(context: Context) : List<GameReview> {
        return withContext(Dispatchers.IO) {
            var db = AppDatabase.getInstance(context)
            var reviews = db!!.reviewDao().getOfflineReviews()
            return@withContext reviews
        }
    }

    suspend fun sendOfflineReviews(context: Context) : Int
    {
        var br : Int=0
        return withContext(Dispatchers.IO) {
            var db = AppDatabase.getInstance(context)
            var reviews = db!!.reviewDao().getOfflineReviews()
            for(gamereview in reviews) {
                var response = AccountApiConfig.retrofit.sendOfflineReviews(acHash,gamereview.igdb_id,gamereview)
                br++
                db!!.reviewDao().updateOfflineReviews(gamereview.igdb_id)
            }
            return@withContext br

        }
    }

    suspend fun getReviewsForGame(igdb_id:Int) : List<GameReview>
    {
        return withContext(Dispatchers.IO) {
            var response = AccountApiConfig.retrofit.getReviewsForGame(igdb_id)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun sendReview(context:Context,gamereview:GameReview):Boolean
    {

        return withContext(Dispatchers.IO)
        {try {
            val savedGames : List<Game> = AccountGamesRepository.getSavedGames()
            var saved : Boolean = false
            for(game in savedGames)
            {
                if(gamereview.igdb_id == game.id)
                {
                    saved = true
                    break
                }
            }
            if(saved==false)
            {
                AccountGamesRepository.saveGame(Game(gamereview.igdb_id,"","","",0.0,
                    "","","","","","", listOf()))
            }
            var response =
                AccountApiConfig.retrofit.sendReview(acHash, gamereview.igdb_id, gamereview)
            return@withContext true
        }
        catch(e:java.lang.Exception)
        {
            var db = AppDatabase.getInstance(context)
            db!!.reviewDao().insertAll(gamereview)
            return@withContext false
        }
        }
    }
}
