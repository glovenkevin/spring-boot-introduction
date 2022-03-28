package com.smu.tes.demo.service

import com.sap.mw.jco.JCO
import com.smu.tes.demo.configuration.SapConnectionConfiguration
import org.springframework.stereotype.Service
import kotlin.system.exitProcess

@Service
class SapConnectionService(val sapConf: SapConnectionConfiguration) {

    fun setClient(): JCO.Client {
        val client = JCO.createClient(
            sapConf.sapClient,
            sapConf.userID,
            sapConf.password,
            sapConf.language,
            sapConf.host,
            sapConf.systemNumber
        )

        try {
            client.connect()
        } catch (e: Exception) {
          e.printStackTrace()
          exitProcess(1)
        }
        return client
    }

}