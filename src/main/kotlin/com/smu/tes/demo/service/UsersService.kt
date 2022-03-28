package com.smu.tes.demo.service

import com.smu.tes.demo.entity.Users
import com.smu.tes.demo.entity.mapper.UsersMapper
import com.smu.tes.demo.exception.UserNotFoundException
import com.smu.tes.demo.model.request.UsersRequest
import com.smu.tes.demo.model.response.UsersDto
import com.smu.tes.demo.repository.UsersRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UsersService (private val usersRepository: UsersRepository) {

    fun getUserById(id: Int): UsersDto {
        val user = usersRepository.findById(id).orElseThrow { UserNotFoundException() }
        val mapper = UsersMapper()
        return mapper.toModel(user)
    }

    fun getUsers(): MutableList<UsersDto> {
        val mapper = UsersMapper()
        val rtnUsers = mutableListOf<UsersDto>()
        val users = usersRepository.findAll()
        users.forEach { user ->
            rtnUsers.add(mapper.toModel(user))
        }
        return rtnUsers
    }

    fun addUser(request: UsersRequest) {
        val mapper = UsersMapper()
        val users = mapper.toEntity(request)
        usersRepository.save(users)
    }

    fun updateUser(request: UsersRequest) {
        val mapper = UsersMapper()
        val users = mapper.toEntity(request)
        usersRepository.save(users)
    }

    fun deleteUser(id: Int) {
        usersRepository.deleteById(id)
    }

}