package com.smu.tes.demo.entity.mapper

import com.smu.tes.demo.entity.Users
import com.smu.tes.demo.model.request.UsersRequest
import com.smu.tes.demo.model.response.UsersDto
import org.springframework.stereotype.Component

@Component
class UsersMapper {

    fun toEntity(request: UsersRequest): Users {
        return Users(
            id = request.id,
            userName = request.userName,
            email = request.email,
            address = request.address,
            phoneNumber = request.phoneNumber,
            password = request.password
        )
    }

    fun toModel(users: Users): UsersDto {
        return UsersDto(
            id = users.id,
            userName = users.userName,
            phoneNumber = users.phoneNumber,
            email = users.email
        )
    }
}