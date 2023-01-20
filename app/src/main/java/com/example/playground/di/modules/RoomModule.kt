package com.example.playground.di.modules

import android.content.Context
import androidx.room.Room
import com.example.playground.room.AppDatabase
import com.example.playground.room.dao.UserDAO
import com.example.playground.room.dao.WeatherHistoryDAO
import com.example.playground.room.repository.user_repository.UserRepository
import com.example.playground.room.repository.user_repository.UserRepositoryImpl
import com.example.playground.room.repository.weather_history_repository.WeatherHistoryRepository
import com.example.playground.room.repository.weather_history_repository.WeatherHistoryRepositoryImpl
import com.example.playground.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext applicationContext: Context) =
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()

    @Provides
    fun providesUserDao(appDatabase: AppDatabase) = appDatabase.userDao()

    @Provides
    @Singleton
    fun providesUserRepository(userDAO: UserDAO) = UserRepositoryImpl(userDAO) as UserRepository

    @Provides
    fun providesWeatherHistoryDao(appDatabase: AppDatabase) = appDatabase.weatherDao()

    @Provides
    @Singleton
    fun providesWeatherHistoryRepository(weatherHistoryDAO: WeatherHistoryDAO) = WeatherHistoryRepositoryImpl(weatherHistoryDAO) as WeatherHistoryRepository


}