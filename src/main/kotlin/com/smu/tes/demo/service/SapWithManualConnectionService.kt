package com.smu.tes.demo.service

import com.smu.tes.demo.exception.SapFunctionNotFound
import org.springframework.stereotype.Service

@Service
class SapWithManualConnectionService (val sapDestinationService: SapDestinationService) {

    fun getFormNhByPO(po: String, module: String, vkbur: String): String {
        if (po.isBlank()) {
            return ""
        }
        val jCoDestination = sapDestinationService.getDestinationByModuleAndVkbur(module, vkbur)

        val functionName = "ZFN_WEB_W_GET_FORMNH_BY_PO"
        val function = jCoDestination.repository.getFunction(functionName) ?: throw SapFunctionNotFound(functionName)

        val tableName = "PI_DATA"
        var sapTable = function.tableParameterList.getTable(tableName)
        sapTable.appendRow()
        sapTable.setValue("EBELN", po)

        function.execute(jCoDestination)
        sapTable = function.tableParameterList.getTable(tableName)
        if (sapTable != null && sapTable.numRows > 0) {
            sapTable.row = 0
            return sapTable.getString("NH_REF")
        }
        return ""
    }

}