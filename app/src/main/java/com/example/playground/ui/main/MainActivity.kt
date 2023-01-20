package com.example.playground.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.playground.R
import com.example.playground.databinding.ActivityMainBinding
import com.example.playground.ui.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initObservables()
        viewModel.getWeatherByCity()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_logout -> {
                logout()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun initViews() {
        supportFragmentManager.apply {
            beginTransaction()
                .add(binding.fragmentContainer.id, MainFragment.newInstance())
                .commit()
        }
    }

    private fun initObservables(){
        viewModel.logoutUserLiveData.observe(this){
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }
    }

    private fun logout(){
        viewModel.logoutUser()
    }
}