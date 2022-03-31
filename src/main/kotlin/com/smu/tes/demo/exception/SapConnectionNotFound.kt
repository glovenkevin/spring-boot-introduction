package com.smu.tes.demo.exception

class SapConnectionNotFound(private val connectionId: String) : RuntimeException() {
    override val message: String
        get() = "Connection $connectionId not Found"
}