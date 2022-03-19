package com.smu.tes.demo.repository

import com.smu.tes.demo.entity.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository: JpaRepository<Users, Int>