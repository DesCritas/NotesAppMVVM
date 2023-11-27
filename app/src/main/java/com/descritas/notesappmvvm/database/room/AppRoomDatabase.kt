package com.descritas.notesappmvvm.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.descritas.notesappmvvm.database.room.dao.NoteRoomDao
import com.descritas.notesappmvvm.model.Note

@Database(entities = [Note::class], version = 1)
abstract class AppRoomDatabase: RoomDatabase() {

    abstract fun getRoomDao(): NoteRoomDao

    companion object{

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance (context: Context): AppRoomDatabase{
            if (INSTANCE == null){
                synchronized(AppRoomDatabase::class){
                    if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppRoomDatabase::class.java,
                            "notes_database"
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}
