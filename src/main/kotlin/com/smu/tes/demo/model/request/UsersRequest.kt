package com.smu.tes.demo.model.request

data class UsersRequest (
    val id: Int,
    val userName: String,
    val email: String,
    val address: String,
    val phoneNumber: String,
    val password: String
)