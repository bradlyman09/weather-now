package com.example.playground

import com.example.playground.room.dao.UserDAO
import com.example.playground.room.data.UserEntity
import com.example.playground.room.repository.user_repository.UserRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UserRepositoryTest {

    private val userDAO = mockk<UserDAO>(relaxed = true)
    private val sut = UserRepositoryImpl(userDAO)

    @Test
    fun `test create user being called successfully`(){
        runBlocking {
            sut.createUser(buildUserEntity())
        }

        coVerify {
            userDAO.insertUser(buildUserEntity())
        }
    }

    @Test
    fun `test login returns userEntity successfully`(){

        coEvery{
            userDAO.getUser(buildUsername(), buildPassword())
        } returns buildUserEntity()

        //initialized with temp values
        var actualResult = UserEntity(
            userId = 0,
            username = "testUsername1",
            pass = "testPass2"
        )

        runBlocking {
            sut.login(buildUsername(), buildPassword())?.let {
                actualResult = it
            }
        }

        println(buildUserEntity())
        println(actualResult)
        assert(buildUserEntity() == actualResult)
    }

    companion object{
        fun buildUserEntity() = UserEntity(
            userId = 1,
            username = "testUsername",
            pass = "testPass"
        )

        fun buildUsername() = "testUsername"
        fun buildPassword() = "testPassword"
    }
}