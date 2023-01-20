package com.example.playground

import com.example.playground.room.dao.WeatherHistoryDAO
import com.example.playground.room.data.WeatherHistoryEntity
import com.example.playground.room.repository.weather_history_repository.WeatherHistoryRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class WeatherHistoryRepositoryTest {

    private val weatherHistoryDAO = mockk<WeatherHistoryDAO>(relaxed = true)
    private val sut = WeatherHistoryRepositoryImpl(weatherHistoryDAO)

    @Test
    fun `test weather dao being called successfully`(){
        runBlocking {
            sut.weatherHistoryDAO.addWeatherHistory(buildWeatherHistory())
        }

        coEvery { weatherHistoryDAO.addWeatherHistory(buildWeatherHistory()) }
    }

    @Test
    fun `test get all weather history returns successfully`(){

        coEvery {
            weatherHistoryDAO.getAllWeatherHistory(buildUserId())
        } returns buildWeatherHistoryList()

        //initialized with temp values
        val actualResult : List<WeatherHistoryEntity>

        runBlocking {
            actualResult = sut.getAllWeatherHistory(buildUserId())
        }

        assert(buildWeatherHistoryList() == actualResult)
    }

    companion object{
        fun buildWeatherHistory() = WeatherHistoryEntity(
            weatherHistoryId = 1,
            userId = 2,
            weather = "testWeather",
            weatherDesc = "testWeatherDesc",
            weatherDate = "testWeatherDate",
            weatherIcon = "testWeatherIcon"
        )

        fun buildUserId() = 3

        fun buildWeatherHistoryList() = listOf(
            WeatherHistoryEntity(
                weatherHistoryId = 1,
                userId = 2,
                weather = "testWeather",
                weatherDesc = "testWeatherDesc",
                weatherDate = "testWeatherDate",
                weatherIcon = "testWeatherIcon"
            ),
            WeatherHistoryEntity(
                weatherHistoryId = 4,
                userId = 5,
                weather = "testWeather1",
                weatherDesc = "testWeatherDesc1",
                weatherDate = "testWeatherDate1",
                weatherIcon = "testWeatherIcon1"
            ),
        )
    }
}