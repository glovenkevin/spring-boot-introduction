package com.smu.tes.demo.configuration

import com.sap.conn.jco.JCoDestination
import com.sap.conn.jco.JCoDestinationManager
import com.sap.conn.jco.ext.DestinationDataProvider
import com.smu.tes.demo.repository.general.SapConfigRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.Transactional
import java.io.File
import java.io.FileOutputStream
import java.util.*

@Configuration
class SapDatasourceConfiguration(
    @Value("\${sap.active-profile}")
    private val activeSapConnection: String
) {

    @Deprecated("This is not a good choice to create SapJCO Connection and should not be used on production")
    fun setJcoDestinationManual(): JCoDestination {
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

    @Bean
    @ConditionalOnExpression("'\${sap.implementation}' == 'yaml' and '\${sap.enabled}' == 'true'")
    fun setJcoDestinationFromYaml(): JCoDestination {
        return JCoDestinationManager.getDestination(activeSapConnection)
    }

    @Bean
    @Transactional("generalTransactionManager")
    @ConditionalOnExpression("'\${sap.implementation}' == 'db' and '\${sap.enabled}' == 'true'")
    fun setJcoDestinationFromDatabase(sapConfigRepository: SapConfigRepository): JCoDestination {
        return JCoDestinationManager.getDestination(activeSapConnection)
    }

}