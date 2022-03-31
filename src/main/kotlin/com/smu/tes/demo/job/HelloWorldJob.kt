package com.smu.tes.demo.job

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
@Profile("job")
class HelloWorldJob {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Scheduled(initialDelay = 1000, fixedRate = 60_000)
    fun helloJob() {
        log.info("Hello World, this is my Job!")
    }

    @Async
    @Scheduled(fixedRate = 60_000)
    fun helloAsyncJob() {
        log.info("Hello World, this is my Async Job!")
    }

    /*
    * <second> <minute> <hour> <day of Month> <month> <day of Week>
    * Source: https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions
    */
    @Scheduled(cron = "0 */2 * * * ?")
    fun helloCronJob() {
        log.info("This job run using cron job every 2mins")
    }

}