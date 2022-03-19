package com.smu.tes.demo.service

import com.smu.tes.demo.entity.Users
import com.smu.tes.demo.exception.UserNotFoundException
import com.smu.tes.demo.model.request.UsersRequest
import com.smu.tes.demo.model.response.UsersDto
import com.smu.tes.demo.repository.UsersRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UsersService (private val usersRepository: UsersRepository) {

    fun getUserById(id : Int): UsersDto {
        val user = usersRepository.findById(id).orElseThrow { UserNotFoundException() }
        val userDto = UsersDto(
            id = user.id,
            userName = user.userName,
            phoneNumber = user.phoneNumber,
            email = user.email
        )
        return userDto
    }

    fun getUsers(): Any {
        val rtnUsers = mutableListOf<UsersDto>()
        val users = usersRepository.findAll()
        users.forEach { user ->
            val userDto = UsersDto(
                userName = user.userName,
                email = user.email,
                id = user.id,
                phoneNumber = user.phoneNumber
            )

            rtnUsers.add(userDto)
        }
        return rtnUsers
    }

    fun addUser(request: UsersRequest) {
        val users = Users(
            id = 0,
            userName = request.userName,
            email = request.email,
            address = request.address,
            phoneNumber = request.phoneNumber,
            password = request.password
        )
        usersRepository.save(users)
    }

    fun updateUser(request: UsersRequest) {
        val users = Users(
            id = request.id,
            userName = request.userName,
            email = request.email,
            address = request.address,
            phoneNumber = request.phoneNumber,
            password = request.password
        )
        usersRepository.save(users)
    }

    fun deleteUser(id: Int) {
        usersRepository.deleteById(id)
    }

}