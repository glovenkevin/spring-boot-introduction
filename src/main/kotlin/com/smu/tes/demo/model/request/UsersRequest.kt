package com.smu.tes.demo.model.request

import com.smu.tes.demo.entity.main.Users

data class UsersRequest (
    val id: Int = 0,
    val userName: String,
    val email: String,
    val address: String,
    val phoneNumber: String,
    val password: String
)

fun UsersRequest.toEntity() = Users(
    id = id,
    userName = userName,
    email = email,
    address = address,
    phoneNumber = phoneNumber,
    password = password
)