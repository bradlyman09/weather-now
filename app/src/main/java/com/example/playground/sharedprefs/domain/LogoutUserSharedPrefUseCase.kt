package com.example.playground.sharedprefs.domain

import com.example.playground.sharedprefs.repository.SharedPrefsRepository
import javax.inject.Inject

class LogoutUserSharedPrefUseCase @Inject constructor(val sharedPrefsRepository: SharedPrefsRepository) {
    operator fun invoke(){
        sharedPrefsRepository.logoutUser()
    }
}