package com.example.playground.sharedprefs.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.playground.room.data.UserEntity
import com.example.playground.utils.Constants
import javax.inject.Inject

class SharedPrefsRepositoryImpl @Inject constructor(val sharedPreference : SharedPreferences) : SharedPrefsRepository() {

    override fun getUserDetails() =
        UserEntity(
            userId = sharedPreference.getInt(Constants.SP_USER_ID_KEY, 0),
            username = sharedPreference.getString(Constants.SP_USER_NAME_KEY, "")?: "",
            pass = sharedPreference.getString(Constants.SP_USER_PASS_KEY, "")?: ""
        )

    override fun setUserDetails(userEntity: UserEntity) {
        sharedPreference.edit(commit = true){
            putInt(Constants.SP_USER_ID_KEY, userEntity.userId)
            putString(Constants.SP_USER_NAME_KEY, userEntity.username)
            putString(Constants.SP_USER_PASS_KEY, userEntity.pass)
        }
    }

    override fun logoutUser() {
        sharedPreference.edit(commit = true){
            putInt(Constants.SP_USER_ID_KEY, 0)
            putString(Constants.SP_USER_NAME_KEY, "")
            putString(Constants.SP_USER_PASS_KEY, "")
        }
    }
}