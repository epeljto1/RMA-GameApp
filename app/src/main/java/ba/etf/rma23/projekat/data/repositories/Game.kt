package ba.etf.rma23.projekat.data.repositories

import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val title: String,
    var platform: String?,
    var releaseDate : String?,
    @SerializedName("rating") val rating: Double,
    var coverImage: String?,
    var esrbRating: String?,
    val developer: String,
    val publisher: String,
    var genre: String?,
    @SerializedName("summary") val description: String,
    val userImpressions: List<UserImpression>,
    @SerializedName("igdb_id") var igdbid: Int = id,
    @SerializedName("release_dates") val releaseDates: List<ReleaseDate>? = emptyList(),
    @SerializedName("age_ratings") val ageRatings: List<AgeRatings>? = emptyList(),
    @SerializedName("platforms") val platforms: List<Platforms>? = emptyList(),
    @SerializedName("genres") val genres: List<Genres>? = emptyList(),
    @SerializedName("cover") val cover: Cover? = Cover(""),
)



