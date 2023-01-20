package com.example.playground.di.modules

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.playground.sharedprefs.repository.SharedPrefsRepository
import com.example.playground.sharedprefs.repository.SharedPrefsRepositoryImpl
import com.example.playground.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefsModule {

    @Singleton
    @Provides
    fun providesSharedPreference(@ApplicationContext applicationContext: Context) =
        applicationContext.getSharedPreferences(
            Constants.SHARED_PREF_NAME,
            MODE_PRIVATE
        )

    @Provides
    fun providesSharePreferenceRepository(sharedPreferences: SharedPreferences) =
        SharedPrefsRepositoryImpl(sharedPreferences) as SharedPrefsRepository

}