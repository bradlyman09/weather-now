package com.example.playground.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.playground.R
import com.example.playground.databinding.FragmentHistoryBinding
import com.example.playground.room.data.WeatherHistoryEntity

class HistoryFragment : Fragment() {
    private val viewModel : MainViewModel by activityViewModels()
    private lateinit var binding : FragmentHistoryBinding
    private val weatherHistoryList = mutableListOf<WeatherHistoryEntity>()
    private val weatherHistoryAdapter : WeatherHistoryAdapter by lazy {
        WeatherHistoryAdapter(requireContext(),
            weatherHistoryList
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservables()
        viewModel.getAllWeatherHistory()
    }

    private fun initViews(){
        binding.rvHistory.apply {
            setHasFixedSize(true)
            adapter = weatherHistoryAdapter
        }
    }

    private fun initObservables(){
        viewModel.weatherHistoryListLiveData.observe(viewLifecycleOwner){
            it?.let {
                weatherHistoryList.clear()
                weatherHistoryList.addAll(it)
                weatherHistoryAdapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }
}