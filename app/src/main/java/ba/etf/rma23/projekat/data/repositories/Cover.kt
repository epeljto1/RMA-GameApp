package ba.etf.rma23.projekat.data.repositories

import com.google.gson.annotations.SerializedName

data class Cover(
    @SerializedName("url") val url: String?
)
