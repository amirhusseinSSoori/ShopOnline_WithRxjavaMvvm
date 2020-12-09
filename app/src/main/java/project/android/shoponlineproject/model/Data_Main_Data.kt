package project.android.ecommers.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import project.android.shoponlineproject.model.Detail

data class Data_Main_Data(
    val Details: ArrayList<Detail>,
    val Error: Error,
    val status: String,
    val timestamp: String
)


data class Error(
    val message: String
)