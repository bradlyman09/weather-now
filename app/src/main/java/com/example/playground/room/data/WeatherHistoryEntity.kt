package com.example.playground.room.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "WeatherHistory")
@Parcelize
data class WeatherHistoryEntity (
    @PrimaryKey(autoGenerate = true)
    val weatherHistoryId : Int = 0,

    @ColumnInfo(name = "userId")
    val userId : Int = 0,

    @ColumnInfo(name = "weather")
    val weather : String = "",

    @ColumnInfo(name = "weatherDesc")
    val weatherDesc : String = "",

    @ColumnInfo(name = "weatherDate")
    val weatherDate : String = "",

    @ColumnInfo(name = "weatherIcon")
    val weatherIcon : String = "",

) : Parcelable