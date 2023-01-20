package com.example.playground.sharedprefs.repository

import com.example.playground.room.data.UserEntity

abstract class SharedPrefsRepository {
    abstract fun getUserDetails() : UserEntity
    abstract fun setUserDetails(userEntity: UserEntity)
    abstract fun logoutUser()
}