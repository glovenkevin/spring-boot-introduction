package com.smu.tes.demo.sapservice

import com.smu.tes.demo.service.SapWithManualConnectionService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("sap")
class SapWithManualConnectionTest (
    @Autowired
    val sapWithManualConnectionService: SapWithManualConnectionService
) {

    private var module = "SD"
    private var vkbur = "AA01"

    @Test
    fun createLocalDestinationAndExecuteFunctionSAPTest() {
        val noPo = "4550022615"
        val expectedFormNh = "NH10487"
        val formNh = sapWithManualConnectionService.getFormNhByPO(noPo, module, vkbur)
        Assertions.assertThat(formNh).isEqualTo(expectedFormNh)
    }

}