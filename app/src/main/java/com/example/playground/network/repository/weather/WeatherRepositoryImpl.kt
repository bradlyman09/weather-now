package com.example.playground.network.repository.weather

import com.example.playground.network.data.response.get_weather.GetWeatherResponse
import com.example.playground.network.services.WeatherService
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    val weatherService: WeatherService
) : WeatherRepository() {

    override suspend fun getWeatherByCity(queryMap : HashMap<String, String>) : GetWeatherResponse {
        return weatherService.getWeatherByCity(queryMap)
    }
}