package com.example.playground.room.domain

import com.example.playground.room.data.UserEntity
import com.example.playground.room.repository.user_repository.UserRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(val userRepository: UserRepository) {
    suspend operator fun invoke(username : String, pass : String) = userRepository.login(username = username, pass =  pass)
}