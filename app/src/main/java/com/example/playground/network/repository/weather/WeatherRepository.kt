package com.example.playground.network.repository.weather

import com.example.playground.network.data.response.get_weather.GetWeatherResponse

abstract class WeatherRepository {
    abstract suspend fun getWeatherByCity(queryMap : HashMap<String, String>) : GetWeatherResponse
}