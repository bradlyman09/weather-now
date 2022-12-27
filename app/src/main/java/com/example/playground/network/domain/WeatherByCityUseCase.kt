package com.example.playground.network.domain

import com.example.playground.network.data.response.get_weather.GetWeatherResponse
import com.example.playground.network.repository.weather.WeatherRepository
import javax.inject.Inject

class WeatherByCityUseCase @Inject constructor(
    val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(queryMap : HashMap<String, String>) : GetWeatherResponse {
        return weatherRepository.getWeatherByCity(queryMap)
    }
}