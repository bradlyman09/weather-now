package com.example.playground.room.domain

import com.example.playground.room.data.UserEntity
import com.example.playground.room.repository.user_repository.UserRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(val userRepository: UserRepository) {
    suspend operator fun invoke(userEntity: UserEntity){
        userRepository.createUser(userEntity = userEntity)
    }
}