package com.example.playground.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.playground.R
import com.example.playground.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    private val viewModel : OnboardingViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservables()
    }

    private fun initViews(){
        binding.btnSignUp.setOnClickListener {
            viewModel.signUp()
        }

        binding.btnLogin.setOnClickListener {
            viewModel.validateLogin(
                tempUsername = binding.etUser.toString().trim(),
                tempPass = binding.etPass.toString().trim()
            )
        }
    }

    private fun initObservables(){

    }

    companion object {

        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}