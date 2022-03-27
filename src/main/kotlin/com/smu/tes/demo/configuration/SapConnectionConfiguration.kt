package com.smu.tes.demo.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "sap.connection")
data class SapConnectionConfiguration(
    var sapClient: String = "",
    var userID: String = "",
    var password: String = "",
    var language: String = "",
    var host: String = "",
    var systemNumber: String = "",
    var r3name: String = "",
)