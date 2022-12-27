package com.example.playground.network.data.response.get_weather

import com.google.gson.annotations.SerializedName

data class GetWeatherResponse(
    @SerializedName("weather")
    val weather : List<Weather>,

    @SerializedName("main")
    val mainTempDetails : MainTempDetails,

    @SerializedName("sys")
    val sys : Sys,

    @SerializedName("timezone")
    val timezone : Long,

    @SerializedName("name")
    val name : String
)