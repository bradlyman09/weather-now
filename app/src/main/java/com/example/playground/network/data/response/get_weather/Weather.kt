package com.example.playground.network.data.response.get_weather

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("id")
    val id : Int,

    @SerializedName("main")
    val main : String,

    @SerializedName("description")
    val description : String,

    @SerializedName("icon")
    val icon : String
)
