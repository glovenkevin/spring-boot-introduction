package com.smu.tes.demo.exception

class ConnectionPropertiesNotFound : RuntimeException() {
    override val message: String
        get() = "Properties Connection Not Found"
}