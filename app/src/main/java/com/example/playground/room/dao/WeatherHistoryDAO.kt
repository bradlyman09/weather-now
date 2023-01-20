package com.example.playground.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.playground.room.data.WeatherHistoryEntity

@Dao
interface WeatherHistoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWeatherHistory(weatherEntity: WeatherHistoryEntity)

    @Query("SELECT * FROM WEATHERHISTORY WHERE userId = :userId ORDER BY weatherHistoryId DESC")
    suspend fun getAllWeatherHistory(userId : Int) : List<WeatherHistoryEntity>
}