package com.smu.tes.demo.service

import com.sap.conn.jco.JCoDestination
import com.smu.tes.demo.exception.SapFunctionNotFound
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.stereotype.Service

@Service
@ConditionalOnExpression("'\${sap.enabled}' == 'true'")
class SapHelloWorldService(
    val jCoDestination: JCoDestination
) {

    fun getHelloWorldSap(): String {
        val functionName = "ZFN_WEB_W_HELLO_WORLD"
        val function = jCoDestination.repository.getFunction(functionName) ?: throw SapFunctionNotFound(functionName)

        function.execute(jCoDestination)
        return function.exportParameterList.getString("E_MESSAGE")
    }

    fun getFormNhByPO(po: String): String {
        if (po.isBlank()) {
            return ""
        }

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