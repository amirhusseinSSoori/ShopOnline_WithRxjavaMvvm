package project.android.shoponlineproject.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import project.android.shoponlineproject.model.Detail


@Database(entities = arrayOf(Detail::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao?

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context?): AppDatabase? {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context!!, AppDatabase::class.java, "room_db")
                        .allowMainThreadQueries().build()
            }
            return instance
        }
    }
}