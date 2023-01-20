package com.example.playground.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.playground.R
import com.example.playground.databinding.ActivityOnboardingBinding
import com.example.playground.ui.main.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        checkAutoLogin()
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

        viewModel.validLogin.observe(this){
            it?.let {
                startActivity(Intent(this@OnboardingActivity, MainActivity::class.java))
                finish()
            }?:run{
                MaterialAlertDialogBuilder(this@OnboardingActivity)
                    .setTitle(getString(R.string.login_invalid_user))
                    .setMessage(getString(R.string.login_invalid_user_pass))
                    .setPositiveButton(getString(R.string.generic_confirm)){ _, _->
                    }
                    .show()
            }
        }
    }

    private fun checkAutoLogin(){
        viewModel.checkIfUserIsLoggedIn()
    }
}