package com.smu.tes.demo.configuration

import com.sap.conn.jco.ext.Environment
import com.smu.tes.demo.repository.general.SapConfigRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class SapDataProviderRegisterConfiguration(
    @Value("\${sap.implementation}")
    private val sapImplementation: String,
    private val yamlSapConnections: YamlSapConnections,
    private val sapConfigRepository: SapConfigRepository
) {

    @PostConstruct
    fun registerDataSourceProvider() {
        if (sapImplementation == "yaml") {
            val connManager = SapConnectionManager(yamlSapConnections)
            val yamlDestinationDataProvider = YamlDestinationDataProvider(connManager)
            Environment.registerDestinationDataProvider(yamlDestinationDataProvider)
        }

        if (sapImplementation == "db") {
            val databaseDestinationDataProvider = DatabaseDestinationDataProvider(sapConfigRepository)
            Environment.registerDestinationDataProvider(databaseDestinationDataProvider)
        }
    }

}