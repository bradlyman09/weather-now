package com.example.playground.ui.onboarding

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.playground.R
import com.example.playground.databinding.FragmentSignupBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SignupFragment : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentSignupBinding
    private val viewModel : OnboardingViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservables()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

    }

    private fun initViews(){
        binding.btnSignUp.setOnClickListener {
            signup()
        }
    }

    private fun initObservables(){
        viewModel.validSignUp.observe(viewLifecycleOwner){
            if (it){
                viewModel.validateConfirmPassword()
            }
            else{
                errorPrompt(title = "Invalid user details",
                    message = "Pls provide all the details"
                )
            }
        }

        viewModel.validPass.observe(viewLifecycleOwner){
            if (it){
                viewModel.signUpComplete()
            }
            else{
                errorPrompt(title = "Invalid Passwords",
                    message = "Passwords needs to be the same")
            }
        }
    }

    private fun signup(){
        viewModel.validateSignUp(
            tempUsername = binding.etUser.text.toString().trim(),
            tempPass = binding.etPass.text.toString().trim(),
            tempConfirmPass = binding.etConfirmPass.text.toString().trim()
        )
    }

    private fun errorPrompt(title : String, message : String){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Confirm"){ _, _->
            }
            .show()
    }

    companion object {
        fun newInstance() = SignupFragment()
    }
}