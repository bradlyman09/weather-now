package com.example.playground.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.playground.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservables()
        viewModel.getWeatherByCity()
    }

    private fun initObservables(){
        viewModel.weather.observe(this){
            binding.tvWeather.text = it
        }

        viewModel.weatherIcon.observe(this){
            Glide.with(this@MainActivity)
                .load(it)
                .into(binding.ivWeatherIcon)
        }

        viewModel.weatherDesc.observe(this){
            binding.tvWeatherDesc.text =  it
        }
    }
}