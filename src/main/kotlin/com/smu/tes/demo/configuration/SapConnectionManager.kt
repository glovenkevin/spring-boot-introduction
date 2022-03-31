package com.smu.tes.demo.configuration

import com.smu.tes.demo.exception.ConnectionPropertiesNotFound
import com.smu.tes.demo.exception.SapConnectionNotFound

class SapConnectionManager(private val yamlSapConnections: YamlSapConnections) : IConnectionManager {

    override fun getCredentials(destinationId: String): ICredential {
        if (yamlSapConnections.connections.isEmpty()) {
            throw ConnectionPropertiesNotFound()
        }

        val conn = yamlSapConnections.connections.find {
            it.connectionId == destinationId
        } ?: throw SapConnectionNotFound(destinationId)

        return SapCredential(conn)
    }

}