package com.smu.tes.demo.configuration

import com.sap.conn.jco.ext.DestinationDataEventListener
import com.sap.conn.jco.ext.DestinationDataProvider
import com.smu.tes.demo.entity.general.toSapConnection
import com.smu.tes.demo.exception.SapConnectionNotFound
import com.smu.tes.demo.repository.general.SapConfigRepository
import org.slf4j.LoggerFactory
import java.util.*

class DatabaseDestinationDataProvider(private val sapConfigRepository: SapConfigRepository) : DestinationDataProvider {

    private val log = LoggerFactory.getLogger(this::class.java)
    private lateinit var listener : DestinationDataEventListener

    override fun getDestinationProperties(destinationParam: String): Properties {
        log.debug("Destination name called: $destinationParam")
        val split = destinationParam.split("-")
        val module = split[0]
        val vkbur = split[1]

        val sapConfig = sapConfigRepository.findByModuleAndVkbur(module, vkbur).orElseThrow { SapConnectionNotFound(destinationParam) }
        val connection = sapConfig.toSapConnection()

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
        return false
    }

    override fun setDestinationDataEventListener(listener: DestinationDataEventListener) {
        this.listener = listener
    }
}