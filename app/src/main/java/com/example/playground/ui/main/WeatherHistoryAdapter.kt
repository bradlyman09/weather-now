package com.example.playground.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.playground.databinding.ItemWeatherHistoryBinding
import com.example.playground.room.data.WeatherHistoryEntity
import kotlinx.android.synthetic.main.item_weather_history.view.*

class WeatherHistoryAdapter constructor(
    private val context: Context,
    private val weatherHistoryList : MutableList<WeatherHistoryEntity>
    ) : RecyclerView.Adapter<WeatherHistoryAdapter.ViewHolder>()
{
    private lateinit var binding : ItemWeatherHistoryBinding

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemWeatherHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(weatherHistoryList[position].weatherIcon)
            .into(holder.itemView.ivWeatherIcon)

        holder.itemView.tvWeather.text = weatherHistoryList[position].weather
        holder.itemView.tvWeatherDesc.text = weatherHistoryList[position].weatherDesc
        holder.itemView.tvWeatherDate.text = weatherHistoryList[position].weatherDate
    }

    override fun getItemCount(): Int = weatherHistoryList.size
}