package com.smu.tes.demo.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "sap")
class YamlSapConnections(
    val connections: List<SapConnection>,
)