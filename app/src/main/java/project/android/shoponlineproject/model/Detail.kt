package project.android.shoponlineproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "details",
    indices = [Index(value = ["id"], unique = true)]
)
data class Detail (



    @PrimaryKey(autoGenerate = true) val uid:Int,




    @ColumnInfo(name = "code") val code: String,


    @ColumnInfo(name = "color")   val color: String,



    @ColumnInfo(name = "cost")  val cost: String,



    @ColumnInfo(name = "description")  val description: String,




    @ColumnInfo(name = "id")    val id: String,



    @ColumnInfo(name = "image")  val image: String,



    @ColumnInfo(name = "name")    val name: String


)