package com.example.playground.room.repository.user_repository

import com.example.playground.room.dao.UserDAO
import com.example.playground.room.data.UserEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val userDAO: UserDAO) : UserRepository() {


    override suspend fun createUser(userEntity: UserEntity){
        userDAO.insertUser(userEntity)
    }

    override suspend fun login(username : String, pass : String) : UserEntity{
        val reponse = userDAO.getUser(username =  username,
            password = pass)
        return reponse
    }
}