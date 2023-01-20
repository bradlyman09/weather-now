package com.example.playground.room.repository.user_repository

import com.example.playground.room.data.UserEntity

abstract class UserRepository {
    abstract suspend fun createUser(userEntity: UserEntity)
    abstract suspend fun login(username : String, pass : String) : UserEntity?
}