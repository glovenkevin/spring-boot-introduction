package com.smu.tes.demo.configuration

interface ICredential {
    var connection : IConnection

    fun getConnectionID() : String
}