package com.smu.tes.demo.entity.main

import com.smu.tes.demo.model.response.UsersDto
import javax.persistence.*

@Entity
@Table(name = "users")
data class Users(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Column(name = "username")
    var userName: String,

    var email: String,
    var address: String,

    @Column(name = "phone_number")
    var phoneNumber: String,

    var password: String
)

fun Users.toModel() = UsersDto (
    id = id,
    userName = userName,
    phoneNumber = phoneNumber,
    email = email
)