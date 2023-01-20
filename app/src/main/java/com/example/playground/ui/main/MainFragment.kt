package com.example.playground.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.playground.R
import com.example.playground.databinding.FragmentMainBinding
import com.example.playground.utils.Constants
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var mainFragmentAdapter: MainFragmentAdapter
    private lateinit var binding : FragmentMainBinding
    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){
        mainFragmentAdapter = MainFragmentAdapter(this)
        binding.vpMain.adapter = mainFragmentAdapter

        TabLayoutMediator(binding.tlMain, binding.vpMain){
            tab, positon ->
            tab.text = when(positon){
                0 ->  Constants.MAIN_TAB_CURRENT_WEATHER
                else -> Constants.MAIN_TAB_HISTORY
            }
        }.attach()

        binding.tvTitle.text =
            String.format(getString(R.string.main_toolbar_greeting),
                viewModel.userDetails.username
            )
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}