package ba.etf.rma23.projekat.data.repositories

import retrofit2.Response
import retrofit2.http.*

interface AccountApi {
    @GET("/account/{aid}/games")
    suspend fun getSavedGames(@Path("aid") aid:String): Response<List<Game>>

    @POST("/account/{aid}/game")
    suspend fun saveGame(@Path("aid") aid:String, @Body game:SaveGameRequest): Response<Game>

    @DELETE("/account/{aid}/game/{gid}")
    suspend fun removeGame(@Path("aid") aid:String, @Path("gid") gid:Int): Response<Unit>

    @GET("/account/{aid}/games")
    suspend fun getGamesContainingString(@Path("aid") aid:String, @Query("name") query:String): Response<List<Game>>
}