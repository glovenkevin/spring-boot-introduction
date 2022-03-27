package com.smu.tes.demo.sapconfiguration

import com.smu.tes.demo.configuration.SapDataSourceConfiguration
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SapDataSourceConfigurationTest @Autowired constructor (
    private val sapDataSourceConfiguration: SapDataSourceConfiguration
) {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Test
    fun testConnect() {
        assertDoesNotThrow {
            val client = sapDataSourceConfiguration.setClient()
            log.info("Connection SAP User: ${client.user}")
            log.info("Connection SAP isAlive: ${client.isAlive}")

            client.disconnect()
        }
    }

}