package com.smu.tes.demo.sapservice

import com.smu.tes.demo.service.SapHelloWorldService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SapHelloWorldServiceTest(
    @Autowired
    val sapHelloWorldService: SapHelloWorldService
) {

    val log = LoggerFactory.getLogger(this::class.java)!!

    @Test
    @Disabled
    fun getHelloWorldSap_Test() {
        val expected = "Hello To Bro!"
        val response = sapHelloWorldService.getHelloWorldSap()
        Assertions.assertThat(response).isEqualTo(expected)
    }

    @Test
    fun setEmptyParameter_Test() {
        val formNh = sapHelloWorldService.getFormNhByPO("")
        Assertions.assertThat(formNh).isEmpty()
    }

    @Test
    fun setRandomParameter_Test() {
        val randomPONum = (0..1000000000).random().toString()
        val formNh = sapHelloWorldService.getFormNhByPO(randomPONum)
        Assertions.assertThat(formNh).isEmpty()
    }

    @Test
    fun getFormNhByPO_Test() {
        val po = "4550022615"
        val expectedFormNh = "NH10487"
        val formNh = sapHelloWorldService.getFormNhByPO(po)

        log.info("Result: $formNh, Expected Result: $expectedFormNh")
        Assertions.assertThat(formNh).isEqualTo(expectedFormNh)
    }

}