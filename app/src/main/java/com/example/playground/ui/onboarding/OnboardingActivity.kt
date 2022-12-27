package com.example.playground.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.playground.databinding.ActivityOnboardingBinding
import com.example.playground.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOnboardingBinding
    private val viewModel : OnboardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initObservables()
    }

    private fun initViews(){
        supportFragmentManager.apply {
            beginTransaction()
                .add(binding.flContainer.id, LoginFragment.newInstance())
                .commit()
        }
    }

    private fun initObservables(){
        viewModel.showSignUpBottomSheet.observe(this){
            SignupFragment.newInstance().show(supportFragmentManager, "")
        }

        viewModel.signupComplete.observe(this){
            startActivity(Intent(this@OnboardingActivity, MainActivity::class.java))
            finish()
        }
    }
}