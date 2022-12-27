package com.example.playground.di.modules

import android.content.Context
import androidx.room.Room
import com.example.playground.room.AppDatabase
import com.example.playground.room.dao.UserDAO
import com.example.playground.room.repository.user_repository.UserRepository
import com.example.playground.room.repository.user_repository.UserRepositoryImpl
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
            "NowWeather"
        ).build()

    @Provides
    fun providesUserDao(appDatabase: AppDatabase) = appDatabase.userDao()

    @Provides
    @Singleton
    fun providesUserRepository(userDAO: UserDAO) = UserRepositoryImpl(userDAO) as UserRepository
}