package com.example.playground.sharedprefs.domain

import com.example.playground.room.data.UserEntity
import com.example.playground.sharedprefs.repository.SharedPrefsRepository
import javax.inject.Inject

class SetUserSharedPrefUseCase @Inject constructor(val sharedPrefsRepository: SharedPrefsRepository) {
    suspend operator fun invoke(userEntity: UserEntity){
        sharedPrefsRepository.setUserDetails(userEntity)
    }
}