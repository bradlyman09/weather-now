package com.example.playground.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playground.room.data.UserEntity
import com.example.playground.room.domain.CreateUserUseCase
import com.example.playground.room.domain.LoginUserUseCase
import com.example.playground.sharedprefs.domain.GetUserSharedPrefUseCase
import com.example.playground.sharedprefs.domain.SetUserSharedPrefUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    val createUserUseCase: CreateUserUseCase,
    val loginUserUseCase: LoginUserUseCase,
    val setUserSharedPrefUseCase: SetUserSharedPrefUseCase,
    val getUserSharedPrefUseCase: GetUserSharedPrefUseCase
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

    private val _validLogin = MutableLiveData<UserEntity?>()
    val validLogin : LiveData<UserEntity?> = _validLogin

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

            validateLogin(username, pass)
        }
    }

    fun validateLogin(tempUsername : String, tempPass : String){
        viewModelScope.launch {
            val user = loginUserUseCase(tempUsername, tempPass)

            user?.let {
                setUserSharedPrefUseCase(it)
            }

            _validLogin.postValue(user)
        }
    }

    fun checkIfUserIsLoggedIn(){
        val userDetails = getUserSharedPrefUseCase()

        if (userDetails.userId != 0){
            _validLogin.postValue(userDetails)
        }
    }
}