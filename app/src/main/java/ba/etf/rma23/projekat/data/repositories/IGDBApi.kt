package ba.etf.rma23.projekat.data.repositories

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface IGDBApi {
    @Headers(
        "Client-ID: kxzvn5xut2g9fidir06bekgrroince",
        "Authorization: Bearer pzd90idrs7vc6tvfttbf28l6ujqmao"
    )
    @POST("games")
    suspend fun getGamesByName(
        @Query("search") search: String,
        @Query("fields") fields: String = "id, name, rating, release_dates.human, " +
                "age_ratings.rating, age_ratings.category, summary, platforms.abbreviation, genres.name, cover.url",
    ): Response<List<Game>>

}