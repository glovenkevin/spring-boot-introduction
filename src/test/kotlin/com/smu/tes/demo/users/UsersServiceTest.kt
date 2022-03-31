package com.smu.tes.demo.users

import com.smu.tes.demo.exception.UserNotFoundException
import com.smu.tes.demo.model.request.UsersRequest
import com.smu.tes.demo.model.request.toEntity
import com.smu.tes.demo.repository.UsersRepository
import com.smu.tes.demo.service.UsersService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UsersServiceTest {

    @Mock private lateinit var usersRepository: UsersRepository
    private lateinit var usersService: UsersService

    @BeforeEach
    internal fun setUp() {
        usersService = UsersService(usersRepository)
    }

    @Test
    fun successGetAllUser() {
        val users = usersService.getUsers()
        assertThat(users).isNotNull
    }

    @Test
    fun successAddUser() {
        val request = UsersRequest(
            id = 0,
            userName = "tess",
            address = "tes address",
            password = "pass",
            email = "tes@email",
            phoneNumber = "123"
        )
        usersService.addUser(request)
        Mockito.verify(usersRepository).save(request.toEntity())
    }

    @Test
    fun failGetUser() {
        assertThrows<UserNotFoundException> {
            usersService.getUserById(10)
        }
    }

    @Test
    fun successDeleteUser() {
        val userId = 1
        usersService.deleteUser(userId)
        Mockito.verify(usersRepository).deleteById(userId)
    }
}