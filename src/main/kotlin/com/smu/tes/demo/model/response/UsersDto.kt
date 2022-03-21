package com.smu.tes.demo.model.response

import com.smu.tes.demo.entity.Users

class UsersDto (
    var id: Int = 0,
    var userName: String = "",
    var phoneNumber: String = "",
    var email: String = "",
) {
    constructor(userEntity: Users) : this() {
        id = userEntity.id
        userName = userEntity.userName
        phoneNumber = userEntity.phoneNumber
        email = userEntity.email
    }
}