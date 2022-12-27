package com.example.playground.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.playground.room.dao.UserDAO
import com.example.playground.room.data.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDAO
}