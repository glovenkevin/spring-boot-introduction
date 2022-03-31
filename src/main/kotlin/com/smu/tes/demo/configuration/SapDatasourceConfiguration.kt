package com.smu.tes.demo.configuration

import com.sap.conn.jco.JCoDestination
import com.sap.conn.jco.JCoDestinationManager
import com.sap.conn.jco.ext.DestinationDataProvider
import com.sap.conn.jco.ext.Environment
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File
import java.io.FileOutputStream
import java.util.*

@Configuration
class SapDatasourceConfiguration(
    @Value("\${sap.active-profile}")
    val activeSapConnection: String,
    val yamlSapConnections: YamlSapConnections
) {

    @Deprecated("This is not a good choice to create SapJCO Connection and should not be used on production")
    fun setJcoDestination(): JCoDestination {
        val configName = ""
        val props = Properties()
        props.setProperty(DestinationDataProvider.JCO_ASHOST, "172.20.3.10")
        props.setProperty(DestinationDataProvider.JCO_SYSNR, "00")
        props.setProperty(DestinationDataProvider.JCO_CLIENT, "SDV")
        props.setProperty(DestinationDataProvider.JCO_USER, "user")
        props.setProperty(DestinationDataProvider.JCO_PASSWD, "password")
        props.setProperty(DestinationDataProvider.JCO_LANG, "EN")
        this.createDataFile(props)

        return JCoDestinationManager.getDestination(configName)
    }

    private fun createDataFile(properties: Properties) {
        val cfg = File("RFC_USER.jcoDestination")
        if (!cfg.exists()) {
            try {
                val fos = FileOutputStream(cfg, false)
                properties.store(fos, "for tests only !")
                fos.close()
            } catch (e: Exception) {
                throw RuntimeException("Unable to create the destination file " + cfg.name, e)
            }
        }
    }

    // Notes: https://answers.sap.com/answers/8555945/view.html
    @Bean
    fun setJcoDestinationFromYaml(): JCoDestination {
        val connManager = SapConnectionManager(yamlSapConnections)
        val yamlDestinationDataProvider = YamlDestinationDataProvider(connManager)
        Environment.registerDestinationDataProvider(yamlDestinationDataProvider)
        return JCoDestinationManager.getDestination(activeSapConnection)
    }

}