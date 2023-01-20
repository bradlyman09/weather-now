package com.example.playground

import com.example.playground.network.data.response.get_weather.GetWeatherResponse
import com.example.playground.network.data.response.get_weather.MainTempDetails
import com.example.playground.network.data.response.get_weather.Sys
import com.example.playground.network.repository.weather.WeatherRepositoryImpl
import com.example.playground.network.services.WeatherService
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryTest {

    private val weatherServiceMock = mockk<WeatherService>(relaxed = true)
    private val sut = WeatherRepositoryImpl(weatherServiceMock)
    @Test
    fun `test weather by city service being called successfully `() {
        val map = HashMap<String, String>()
        map["test"] = "test"

//        coEvery {
//            weatherServiceMock.getWeatherByCity(map)
//
//        } returns buildGetWeatherResponse()

        runBlocking {
            sut.getWeatherByCity(map)
        }

        coVerify {
            weatherServiceMock.getWeatherByCity(map)
        }
    }

    companion object{
        fun buildGetWeatherResponse() = GetWeatherResponse(
            weather = emptyList(),
            mainTempDetails = MainTempDetails(1F),
            datetime = 0L,
            sys = Sys(
                country = "testCountry",
                sunrise = 1L,
                sunset = 2L
            ),
            timezone = 3L,
            name = "testName"
        )
    }
}