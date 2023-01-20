package com.example.playground.di.modules

import com.example.playground.utils.WeatherMapManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object WeatherMapModule {

    @Provides
    fun providesWeatherMapManager() = WeatherMapManager()
}