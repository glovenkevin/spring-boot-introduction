package com.smu.tes.demo.configuration

class SapCredential(override var connection: IConnection) : ICredential {

    override fun getConnectionID(): String {
        return this.connection.connectionId
    }

}