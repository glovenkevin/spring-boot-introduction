package com.smu.tes.demo.exception

import java.lang.Exception

class UserNotFoundException: RuntimeException() {
    override val message: String?
        get() = "User not found"
}