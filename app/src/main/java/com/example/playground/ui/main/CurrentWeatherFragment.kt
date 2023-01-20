package com.example.playground.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.playground.R
import com.example.playground.databinding.FragmentCurrentWeatherBinding
import com.example.playground.utils.WeatherMapManager
import com.example.playground.utils.timestampToTime
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CurrentWeatherFragment : Fragment() {
    private val viewModel : MainViewModel by activityViewModels()
    private lateinit var binding : FragmentCurrentWeatherBinding

    @Inject
    lateinit var weatherMapManager: WeatherMapManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
    }

    private fun initObservables(){
        viewModel.weatherDetailsLiveData.observe(viewLifecycleOwner){ getWeatherResponse ->
            getWeatherResponse.weather.firstOrNull()?.let {
                Glide.with(requireContext())
                    .load(weatherMapManager.getWeatherIcon(it.icon))
                    .into(binding.ivWeatherIcon)

                binding.tvWeatherValue.text = it.main
                binding.tvWeatherDescValue.text = it.description
            }

            binding.tvLocationValue.text =
                String.format(getString(R.string.current_weather_location),
                    getWeatherResponse.name,
                    getWeatherResponse.sys.country)

            binding.tvTempValue.text = String.format(getString(R.string.current_weather_temp),
                getWeatherResponse.mainTempDetails.temp)

            binding.tvSunriseValue.text = getWeatherResponse.sys.sunrise.timestampToTime()
            binding.tvSunsetValue.text = getWeatherResponse.sys.sunset.timestampToTime()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = CurrentWeatherFragment()
    }
}