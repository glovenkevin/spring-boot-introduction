package com.smu.tes.demo.configuration

import com.sap.conn.jco.ext.DestinationDataEventListener
import com.sap.conn.jco.ext.DestinationDataProvider
import java.util.*

/* Notes: Idea Source https://stackoverflow.com/a/3524861/6759373 */
class YamlDestinationDataProvider(private val connectionManager : IConnectionManager) : DestinationDataProvider {

    private lateinit var listener : DestinationDataEventListener

    override fun getDestinationProperties(destinationId: String): Properties? {
        val credential = connectionManager.getCredentials(destinationId) ?: return null
        val connection = credential.connection

        val props = Properties()
        props.setProperty(DestinationDataProvider.JCO_ASHOST, connection.host)
        props.setProperty(DestinationDataProvider.JCO_SYSNR, connection.systemNumber)
        props.setProperty(DestinationDataProvider.JCO_CLIENT, connection.sapClient)
        props.setProperty(DestinationDataProvider.JCO_USER, connection.userID)
        props.setProperty(DestinationDataProvider.JCO_PASSWD, connection.password)
        props.setProperty(DestinationDataProvider.JCO_LANG, connection.language)

        if (connection.type == "pool") {
            props.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, connection.minPool)
            props.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, connection.maxPool)
        }

        return props
    }

    override fun supportsEvents(): Boolean {
        return true
    }

    override fun setDestinationDataEventListener(listener: DestinationDataEventListener) {
        this.listener = listener
    }

    fun deleteDestination(destination: String) {
        this.listener.deleted(destination)
    }

    fun updateDestination(destination: String) {
        this.listener.updated(destination)
    }
}