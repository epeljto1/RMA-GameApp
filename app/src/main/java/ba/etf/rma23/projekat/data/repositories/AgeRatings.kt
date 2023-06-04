package ba.etf.rma23.projekat.data.repositories

import com.google.gson.annotations.SerializedName

data class AgeRatings(
    @SerializedName("category") val category:Int?,
    @SerializedName("rating") val rating:Int?
)

