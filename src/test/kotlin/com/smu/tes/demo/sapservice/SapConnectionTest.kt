package com.smu.tes.demo.sapservice

import com.sap.conn.jco.JCoException
import com.smu.tes.demo.service.SapDestinationService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("sap")
class SapConnectionTest (
    @Autowired
    val sapDestinationService: SapDestinationService,
) {

    private var module = "SD"
    private var vkbur = "AA01"

    @Test
    fun createLocalDestinationTest() {
        val destinationConn = sapDestinationService.getDestinationByModuleAndVkbur(module, vkbur)
        assertThat(destinationConn.destinationName).isEqualTo("$module-$vkbur")
    }

    @Test
    fun destinationNotFoundTest() {
        val destinationNotFoundException = assertThrows<JCoException> {
            sapDestinationService.getDestinationByModuleAndVkbur(module = "WM", vkbur = "AA02")
        }
        assertThat(destinationNotFoundException.message).isEqualTo("Destination WM-AA02 does not exist")
    }
}