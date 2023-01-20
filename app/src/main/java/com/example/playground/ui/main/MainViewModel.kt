package com.example.playground.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playground.network.data.response.get_weather.GetWeatherResponse
import com.example.playground.network.domain.WeatherByCityUseCase
import com.example.playground.room.data.UserEntity
import com.example.playground.room.data.WeatherHistoryEntity
import com.example.playground.room.domain.AddWeatherHistoryUseCase
import com.example.playground.room.domain.GetAllWeatherHistoryUseCase
import com.example.playground.sharedprefs.domain.GetUserSharedPrefUseCase
import com.example.playground.sharedprefs.domain.LogoutUserSharedPrefUseCase
import com.example.playground.utils.WeatherMapManager
import com.example.playground.utils.timestampToDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val weatherByCityUseCase: WeatherByCityUseCase,
    val getUserSharedPrefUseCase: GetUserSharedPrefUseCase,
    val logoutUserSharedPrefUseCase: LogoutUserSharedPrefUseCase,
    val addWeatherHistoryUseCase: AddWeatherHistoryUseCase,
    val getAllWeatherHistoryUseCase: GetAllWeatherHistoryUseCase,
    val weatherMapManager: WeatherMapManager
) : ViewModel(){

    private val _weatherDetailsLiveData = MutableLiveData<GetWeatherResponse>()
    val weatherDetailsLiveData : LiveData<GetWeatherResponse> = _weatherDetailsLiveData

    private val _weatherHistoryListLiveData = MutableLiveData<List<WeatherHistoryEntity>?>()
    val weatherHistoryListLiveData : LiveData<List<WeatherHistoryEntity>?> = _weatherHistoryListLiveData

    private val _logoutUserLiveData = MutableLiveData<Boolean>()
    val logoutUserLiveData : LiveData<Boolean> = _logoutUserLiveData

    val userDetails : UserEntity by lazy {
        getUserSharedPrefUseCase()
    }

    fun getWeatherByCity(){
        viewModelScope.launch {
            val queryMap = HashMap<String, String>()
            queryMap["q"] = "Manila"
            queryMap["appid"] = "f1972c30f01df13f61f0325dbbcbabea"
            queryMap["units"] = "metric"

            weatherByCityUseCase(queryMap)?.let {
                val weather = it.weather.firstOrNull()
                addWeatherHistory(
                    WeatherHistoryEntity(
                        userId = userDetails.userId,
                        weather = weather?.main?:"",
                        weatherDesc = weather?.description?:"",
                        weatherDate = it.datetime.timestampToDate(),
                        weatherIcon = weatherMapManager.getWeatherIcon(weather?.icon?:"")
                    )
                )
                _weatherDetailsLiveData.postValue(it)
            }
        }
    }

    fun logoutUser(){
        logoutUserSharedPrefUseCase()
        _logoutUserLiveData.postValue(true)
    }

    fun addWeatherHistory(weatherEntity: WeatherHistoryEntity){
        viewModelScope.launch {
            addWeatherHistoryUseCase(weatherEntity)
        }
    }

    fun getAllWeatherHistory() {
        viewModelScope.launch {
            _weatherHistoryListLiveData.postValue(getAllWeatherHistoryUseCase(userDetails.userId))
        }
    }
}