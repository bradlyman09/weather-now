package com.example.playground.network.data.response.get_weather

import com.google.gson.annotations.SerializedName

data class MainTempDetails(
    @SerializedName("temp")
    val temp : Float
)
