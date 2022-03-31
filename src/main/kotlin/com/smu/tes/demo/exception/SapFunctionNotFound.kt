package com.smu.tes.demo.exception

class SapFunctionNotFound(private val functionName: String) : RuntimeException() {
    override val message: String
        get() = "Function $functionName not found in SAP"
}