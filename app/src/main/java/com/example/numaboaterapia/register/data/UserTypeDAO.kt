package com.example.numaboaterapia.register.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.numaboaterapia.register.userType.data.UserTypeEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface UserTypeDAO {

    @Query("SELECT * FROM user_type_table LIMIT 1")
    fun getUserType(): Flow<UserTypeEnum>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserType(userTypeEnum: UserTypeEnum)

    @Query("DELETE FROM user_type_table")
    suspend fun deleteUserType()

}

