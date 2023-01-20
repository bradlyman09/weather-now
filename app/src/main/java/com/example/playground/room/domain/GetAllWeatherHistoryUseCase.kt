package com.example.playground.room.domain

import com.example.playground.room.repository.weather_history_repository.WeatherHistoryRepository
import javax.inject.Inject

class GetAllWeatherHistoryUseCase @Inject constructor(val weatherHistoryRepository: WeatherHistoryRepository) {

    suspend operator fun invoke(userId : Int) = weatherHistoryRepository.getAllWeatherHistory(userId)
}