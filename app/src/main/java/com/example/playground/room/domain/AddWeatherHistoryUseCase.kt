package com.example.playground.room.domain

import com.example.playground.room.data.WeatherHistoryEntity
import com.example.playground.room.repository.weather_history_repository.WeatherHistoryRepository
import javax.inject.Inject

class AddWeatherHistoryUseCase @Inject constructor(val weatherHistoryRepository: WeatherHistoryRepository) {

    suspend operator fun invoke(weatherEntity: WeatherHistoryEntity) =
        weatherHistoryRepository.addWeatherHistory(weatherEntity)
}