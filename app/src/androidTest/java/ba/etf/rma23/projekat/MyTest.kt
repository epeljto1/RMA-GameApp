package ba.etf.rma23.projekat

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import ba.etf.rma23.projekat.data.repositories.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyTest {
    private lateinit var gameReviewDao: GameReviewDao
    private lateinit var appDatabase: AppDatabase
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        appDatabase = AppDatabase.getInstance(context)
        gameReviewDao = appDatabase.reviewDao()
    }

    @After
    fun teardown() {
        appDatabase.close()
    }

    @Test
    fun insertGameReview() = runBlocking {
        // Create a GameReview object
        val gameReview = GameReview(
            id = 1,
            rating = 5,
            review = "Good",
            igdb_id = 1160,
            online = false
        )
        val gameReview2 = GameReview(
            id = 2,
            rating = 3,
            review = "Not bad",
            igdb_id = 1160,
            online = false
        )
        val gameReview3 = GameReview(4,
            "Okay",
            1160, false,"",""
        )

       //  gameReviewDao.deleteOffline()

        // gameReviewDao.insertAll(gameReview3)
        // Retrieve the inserted GameReview from the database
        // val insertedReview = gameReviewDao.getOfflineReviews()[0]
         gameReviewDao.deleteOffline()
         gameReviewDao.deleteOnline()
        // GameReviewsRepository.sendReview(context,gameReview3)
         val all = gameReviewDao.getAll()
         val empty = emptyList<GameReview>()

        // Check if the retrieved GameReview matches the inserted GameReview
         assertThat(all, `is`(equalTo(empty)))
    }
}