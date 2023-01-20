package com.example.playground.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.playground.room.dao.UserDAO
import com.example.playground.room.dao.WeatherHistoryDAO
import com.example.playground.room.data.UserEntity
import com.example.playground.room.data.WeatherHistoryEntity

@Database(entities = [UserEntity::class, WeatherHistoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDAO
    abstract fun weatherDao() : WeatherHistoryDAO
}