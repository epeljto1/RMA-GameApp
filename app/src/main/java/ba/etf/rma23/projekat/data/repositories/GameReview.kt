package ba.etf.rma23.projekat.data.repositories

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

@Entity(tableName = "gamereview")
data class GameReview (
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name="rating") @SerializedName("rating") var rating: Int?,
    @ColumnInfo(name="review") @SerializedName("review") var review: String?,
    @ColumnInfo(name="igdb_id") var igdb_id: Int,
    @ColumnInfo(name="online") var online: Boolean,
    @Ignore var student: String? ="",
    @Ignore var timestamp: String?="",
)
{
    constructor() : this(0, 0, "", 0, false)

    constructor(rating:Int?,review:String?,igdb_id:Int,online:Boolean,student: String?,timestamp: String?)
            : this(0,rating,review,igdb_id,online,student,timestamp)

    constructor(igdb_id:Int,online:Boolean,student: String?,timestamp: String?)
    : this(0,null,null,igdb_id,online,student,timestamp)

    constructor(rating:Int?,igdb_id:Int,online:Boolean,student: String?,timestamp: String?)
            : this(0,rating,null,igdb_id,online,student,timestamp)

    constructor(review:String?,igdb_id:Int,online:Boolean,student: String?,timestamp: String?)
            : this(0,null,review,igdb_id,online,student,timestamp)

}