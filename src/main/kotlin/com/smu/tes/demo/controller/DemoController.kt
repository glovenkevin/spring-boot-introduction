package com.smu.tes.demo.controller

import com.smu.tes.demo.model.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("hello/world")
class DemoController {

    @GetMapping
    @Async
    fun helloWorld(): CompletableFuture<Any> {
        return CompletableFuture.completedFuture(ResponseEntity<Any>(BaseResponse(message = "Hello World"), HttpStatus.OK))
    }

}