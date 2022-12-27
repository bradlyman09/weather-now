package com.example.playground.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.playground.room.data.UserEntity

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM User " +
            "WHERE username = :username " +
            "AND password = :password " +
            "LIMIT 1")
    suspend fun getUser(username : String, password : String) : UserEntity


}