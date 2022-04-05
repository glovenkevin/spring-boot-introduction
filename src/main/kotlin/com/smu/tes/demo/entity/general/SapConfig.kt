package com.smu.tes.demo.entity.general

import com.smu.tes.demo.configuration.SapConnection
import javax.persistence.*

@Entity
@Table(name = "m_sap_config", schema = "websap_general")
data class SapConfig (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Column(name = "connection_id")
    var connectionId: String = "",

    @Column(name = "user_id")
    var userId: String = "",
    var password: String = "",
    var client: String = "",
    var language: String = "",
    var host: String = "",

    @Column(name="system_number")
    var systemNumber: String = "",
    var type: String = "",

    @Column(name = "min_pool")
    var minPool: String = "",

    @Column(name = "max_pool")
    var maxPool: String = "",

    var module: String = "",
    var vkbur: String = "",
)

fun SapConfig.toSapConnection() = SapConnection(
    connectionId = connectionId,
    userID = userId,
    password = password,
    sapClient = client,
    language = language,
    host = host,
    systemNumber = systemNumber,
    type = type,
    minPool = minPool,
    maxPool = maxPool
)
