package com.example.numaboaterapia.register.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.numaboaterapia.register.userType.data.UserTypeEnum

@Database(entities = arrayOf(UserTypeEnum::class), version = 1, exportSchema = false)
public abstract class UserTypeRoomDataBase : RoomDatabase() {

    abstract fun UserTypeDAO() : UserTypeDAO


    companion object{
        private var INSTANCE : UserTypeRoomDataBase? =  null

        fun getDataBase(context : Context):UserTypeRoomDataBase{
             return  INSTANCE?: synchronized(this){
                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     UserTypeRoomDataBase::class.java,
                     "user_type_database"
                 ).build()
                 INSTANCE = instance
                 instance
             }
        }

    }
}