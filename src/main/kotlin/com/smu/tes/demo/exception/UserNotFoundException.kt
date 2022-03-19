package com.smu.tes.demo.exception

import java.lang.Exception

class UserNotFoundException: Exception() {
    override val message: String?
        get() = "User not found"
}