package com.example.numaboaterapia.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.numaboaterapia.register.pacient.view.PatientRegistrationData

@Database(entities = [PatientRegistrationData::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {


    companion object{
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null
        fun getDatabase(context: Context): AppRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }

    }
}