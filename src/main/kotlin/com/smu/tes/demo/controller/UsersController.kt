package com.smu.tes.demo.controller

import com.smu.tes.demo.model.BaseResponse
import com.smu.tes.demo.model.request.UsersRequest
import com.smu.tes.demo.service.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("v1/users")
class UsersController(private val usersService: UsersService) {

    @GetMapping("{id}")
    @Async
    fun getUser(@PathVariable("id") id: Int): CompletableFuture<Any> {
        return try {

            val user = usersService.getUserById(id)
            CompletableFuture.completedFuture(ResponseEntity<Any>(BaseResponse(data = user), HttpStatus.OK))

        } catch (e: Exception) {
            CompletableFuture.completedFuture(ResponseEntity<Any>(BaseResponse(message = e.message!!), HttpStatus.BAD_REQUEST))
        }
    }

    @GetMapping
    @Async
    fun getUsers(): CompletableFuture<Any> {
        return try {

            val user = usersService.getUsers()
            CompletableFuture.completedFuture(ResponseEntity<Any>(BaseResponse(data = user), HttpStatus.OK))

        } catch (e: Exception) {
            CompletableFuture.completedFuture(ResponseEntity<Any>(BaseResponse(message = e.message!!), HttpStatus.BAD_REQUEST))
        }
    }

    @PutMapping
    @Async
    fun addUser(@RequestBody param: UsersRequest): CompletableFuture<Any> {
        return try {

            usersService.addUser(param)
            CompletableFuture.completedFuture(ResponseEntity<Any>(BaseResponse(), HttpStatus.CREATED))

        } catch (e: Exception) {
            CompletableFuture.completedFuture(ResponseEntity<Any>(BaseResponse(message = e.message!!), HttpStatus.BAD_REQUEST))
        }
    }

    @PostMapping
    @Async
    fun updateUser(@RequestBody param: UsersRequest): CompletableFuture<Any> {
        return try {

            usersService.updateUser(param)
            CompletableFuture.completedFuture(ResponseEntity<Any>(BaseResponse(), HttpStatus.ACCEPTED))

        } catch (e: Exception) {
            CompletableFuture.completedFuture(ResponseEntity<Any>(BaseResponse(message = e.message!!), HttpStatus.BAD_REQUEST))
        }
    }

    @DeleteMapping("{id}")
    @Async
    fun delUser(@PathVariable("id") id: Int): CompletableFuture<Any> {
        return try {

            usersService.deleteUser(id)
            CompletableFuture.completedFuture(ResponseEntity<Any>(BaseResponse(), HttpStatus.ACCEPTED))

        } catch (e: Exception) {
            CompletableFuture.completedFuture(ResponseEntity<Any>(BaseResponse(message = e.message!!), HttpStatus.BAD_REQUEST))
        }
    }
}