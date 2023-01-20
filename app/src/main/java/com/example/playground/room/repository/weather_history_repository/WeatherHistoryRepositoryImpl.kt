package com.example.playground.room.repository.weather_history_repository

import com.example.playground.room.dao.WeatherHistoryDAO
import com.example.playground.room.data.WeatherHistoryEntity
import javax.inject.Inject

class WeatherHistoryRepositoryImpl @Inject constructor(val weatherHistoryDAO: WeatherHistoryDAO) : WeatherHistoryRepository() {

    override suspend fun addWeatherHistory(weatherEntity: WeatherHistoryEntity)  =
        weatherHistoryDAO.addWeatherHistory(weatherEntity)

    override suspend fun getAllWeatherHistory(userId : Int) =
        weatherHistoryDAO.getAllWeatherHistory(userId)
}