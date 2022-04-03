package com.smu.tes.demo.service

import com.sap.conn.jco.JCoDestination
import com.sap.conn.jco.JCoDestinationManager
import org.springframework.stereotype.Service

@Service
class SapDestinationService {

    fun getDestinationByModuleAndVkbur(module: String, vkbur: String): JCoDestination {
        return JCoDestinationManager.getDestination("$module-$vkbur")
    }

}