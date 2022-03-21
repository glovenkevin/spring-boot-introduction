package com.smu.tes.demo.users

import com.smu.tes.demo.exception.UserNotFoundException
import com.smu.tes.demo.model.request.UsersRequest
import com.smu.tes.demo.repository.UsersRepository
import com.smu.tes.demo.service.UsersService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class UsersServiceTest @Autowired constructor (
    private val usersService: UsersService,
    private val usersRepository: UsersRepository
) {

    private val email = "tes@email"

    @Test
    fun successAddUser() {
        val request = UsersRequest(
            userName = "tess",
            address = "tes address",
            password = "pass",
            email = "tes@email",
            phoneNumber = "123"
        )

        usersService.addUser(request)

        val user = usersRepository.findByEmail(email).orElseThrow { UserNotFoundException() }
        assertThat(user.email).isEqualTo(email)
    }

    @Test
    fun failGetUser() {
        assertThrows<UserNotFoundException> {
            usersService.getUserById(10)
        }
    }
}