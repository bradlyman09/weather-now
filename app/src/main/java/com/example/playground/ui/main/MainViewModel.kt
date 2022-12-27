package com.example.playground.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playground.network.domain.WeatherByCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val weatherByCityUseCase: WeatherByCityUseCase
) : ViewModel(){

    private val _weather = MutableLiveData<String>()
    val weather : LiveData<String> = _weather

    private val _weatherIcon = MutableLiveData<String>()
    val weatherIcon : LiveData<String> = _weatherIcon

    private val _weatherDesc = MutableLiveData<String>()
    val weatherDesc : LiveData<String> = _weatherDesc

    fun getWeatherByCity(){
        viewModelScope.launch {
            val queryMap = HashMap<String, String>()
            queryMap["q"] = "Manila"
            queryMap["appid"] = "f1972c30f01df13f61f0325dbbcbabea"
            queryMap["units"] = "metric"

            val response = weatherByCityUseCase(queryMap)

            val weather = response.weather.firstOrNull()
            _weather.postValue(weather?.main ?: "")
            _weatherDesc.postValue(weather?.description ?: "")
            _weatherIcon.postValue("https://openweathermap.org/img/wn/${weather?.icon}@2x.png")
        }
    }
}