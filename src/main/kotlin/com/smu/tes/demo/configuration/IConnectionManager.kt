package com.smu.tes.demo.configuration

interface IConnectionManager {
    fun getCredentials(destinationId: String) : ICredential?
}