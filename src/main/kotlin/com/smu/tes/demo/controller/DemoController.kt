package com.smu.tes.demo.controller

import com.smu.tes.demo.model.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("hello/world")
class DemoController {

    @GetMapping(produces = ["application/json"], name = "Hello World Path")
    @CrossOrigin(maxAge = 3600)
    @Async
    fun helloWorld(): CompletableFuture<Any> {
        return CompletableFuture.completedFuture(ResponseEntity<Any>(BaseResponse(message = "Hello World"), HttpStatus.OK))
    }

}