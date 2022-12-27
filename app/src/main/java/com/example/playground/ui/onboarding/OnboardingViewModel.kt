package com.example.playground.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playground.room.data.UserEntity
import com.example.playground.room.domain.CreateUserUseCase
import com.example.playground.room.domain.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    val createUserUseCase: CreateUserUseCase,
    val loginUserUseCase: LoginUserUseCase
) : ViewModel(){

    private var username = ""
    private var pass = ""
    private var confirmPass = ""

    private val _showSignUpBottomSheet = MutableLiveData<Boolean>()
    val showSignUpBottomSheet : LiveData<Boolean> = _showSignUpBottomSheet

    private val _validSignUp = MutableLiveData<Boolean>()
    val validSignUp : LiveData<Boolean> = _validSignUp

    private val _validPass = MutableLiveData<Boolean>()
    val validPass : LiveData<Boolean> = _validPass

    private val _signupComplete = MutableLiveData<Boolean>()
    val signupComplete : LiveData<Boolean> = _signupComplete

    private val _validLogin = MutableLiveData<Boolean>()
    val validLogin : LiveData<Boolean> = _validLogin

    fun signUp(){
        _showSignUpBottomSheet.postValue(true)
    }

    fun validateSignUp(tempUsername : String, tempPass : String, tempConfirmPass : String) {
        val isValid = when{
            tempUsername.isEmpty()
                    || tempPass.isEmpty()
                    || tempConfirmPass.isEmpty() -> false
            else -> {
                username = tempUsername
                pass = tempPass
                confirmPass = tempConfirmPass

                true
            }
        }

        _validSignUp.postValue(isValid)
    }

    fun validateConfirmPassword(){
        _validPass.postValue(pass == confirmPass)
    }

    fun signUpComplete(){
        viewModelScope.launch {
            createUserUseCase(UserEntity(
                username = username,
                pass = pass
            ))
        }

        _signupComplete.postValue(true)
    }

    fun validateLogin(tempUsername : String, tempPass : String){
        viewModelScope.launch {
            val user = loginUserUseCase(tempUsername, tempPass)
//            val isValid = when{
//                tempUsername.isEmpty() || tempPass.isEmpty() -> false
//                loginUserUseCase(tempUsername, tempPass) != null-> {
//
//                    true
//                }
//            }
            println(user)
        }
//
//        _validLogin.postValue(isValid)
    }
}