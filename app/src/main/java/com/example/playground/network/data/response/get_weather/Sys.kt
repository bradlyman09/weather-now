package com.example.playground.network.data.response.get_weather

import com.google.gson.annotations.SerializedName

data class Sys (
        @SerializedName("country")
        val country : String,

        @SerializedName("sunrise")
        val sunrise : Long,

        @SerializedName("sunset")
        val sunset : Long
)
