package com.example.playground.network.services

import com.example.playground.network.data.response.get_weather.GetWeatherResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherService {
    @GET("/data/2.5/weather")
    suspend fun getWeatherByCity(@QueryMap querymap : Map<String, String>) : GetWeatherResponse
}