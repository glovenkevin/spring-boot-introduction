package com.smu.tes.demo.service

import com.smu.tes.demo.entity.toModel
import com.smu.tes.demo.exception.UserNotFoundException
import com.smu.tes.demo.model.request.UsersRequest
import com.smu.tes.demo.model.request.toEntity
import com.smu.tes.demo.model.response.UsersDto
import com.smu.tes.demo.repository.UsersRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UsersService (private val usersRepository: UsersRepository) {

    fun getUserById(id: Int): UsersDto {
        val user = usersRepository.findById(id).orElseThrow { UserNotFoundException() }
        return user.toModel()
    }

    fun getUsers(): MutableList<UsersDto> {
        val rtnUsers = mutableListOf<UsersDto>()
        val users = usersRepository.findAll()
        users.forEach { user ->
            rtnUsers.add(user.toModel())
        }
        return rtnUsers
    }

    fun addUser(request: UsersRequest) {
        val users = request.toEntity()
        usersRepository.save(users)
    }

    fun updateUser(request: UsersRequest) {
        val users = request.toEntity()
        usersRepository.save(users)
    }

    fun deleteUser(id: Int) {
        usersRepository.deleteById(id)
    }

}