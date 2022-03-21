package com.smu.tes.demo.users

import com.smu.tes.demo.entity.Users
import com.smu.tes.demo.exception.UserNotFoundException
import com.smu.tes.demo.repository.UsersRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest (
    @Autowired
    private val usersRepository: UsersRepository
) {

    private val email = "jj@jj"

    @BeforeAll
    fun prepare() {
        val user = Users(
            email = email,
            userName = "jj",
            phoneNumber = "asdf",
            password = "tes",
            address = "tes"
        )
        usersRepository.save(user)
    }

    @AfterAll
    fun clean() {
        val user = usersRepository.findByEmail(email).orElseThrow { UserNotFoundException() }
        usersRepository.deleteById(user.id)
    }

    @Test
    fun userShouldExist() {
        val expected = usersRepository.selectEmailIsExist(email)
        assertThat(expected).isTrue
    }

    @Test
    fun userShouldUpdated() {
        val user = usersRepository.findByEmail(email).orElseThrow { UserNotFoundException() }
        user.phoneNumber = "123"

        usersRepository.save(user)
        val usersUpdated = usersRepository.findByEmail(email).orElseThrow { UserNotFoundException() }
        assertThat(usersUpdated.phoneNumber).isEqualTo("123")
    }

    @Test
    fun userNotExist() {
        assertThrows<UserNotFoundException> {
            usersRepository.findById(10).orElseThrow { UserNotFoundException() }
        }
    }

    @Test
    fun userSuccessDeleted() {
        val user = usersRepository.findByEmail(email).orElseThrow { UserNotFoundException() }
        assertDoesNotThrow {
            usersRepository.deleteById(user.id)
        }
    }
}