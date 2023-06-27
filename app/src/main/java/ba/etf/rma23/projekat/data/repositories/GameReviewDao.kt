package ba.etf.rma23.projekat.data.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface GameReviewDao {
    @Transaction
    @Query("SELECT * FROM gamereview WHERE online = false")
    fun getOfflineReviews(): List<GameReview>

    @Transaction
    @Query("SELECT * FROM gamereview")
    fun getAll(): List<GameReview>

    @Insert
    fun insertAll(vararg review: GameReview)

    @Query("UPDATE gamereview SET online = true WHERE igdb_id = :gameId AND online = false")
    fun updateOfflineReviews(gameId : Int)

    @Query("DELETE FROM gamereview WHERE online = true")
    fun deleteOnline()

    @Query("DELETE FROM gamereview WHERE online = false")
    fun deleteOffline()
}