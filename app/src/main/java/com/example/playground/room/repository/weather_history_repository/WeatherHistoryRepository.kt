package com.example.playground.room.repository.weather_history_repository

import com.example.playground.room.data.WeatherHistoryEntity

abstract class WeatherHistoryRepository {
    abstract suspend fun addWeatherHistory(weatherEntity: WeatherHistoryEntity)
    abstract suspend fun getAllWeatherHistory(userId : Int) : List<WeatherHistoryEntity>
}