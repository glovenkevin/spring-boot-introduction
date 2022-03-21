package com.smu.tes.demo.model.request

data class UsersRequest (
    val id: Int = 0,
    val userName: String,
    val email: String,
    val address: String,
    val phoneNumber: String,
    val password: String
)