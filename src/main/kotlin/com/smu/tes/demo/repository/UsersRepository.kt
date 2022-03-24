package com.smu.tes.demo.repository

import com.smu.tes.demo.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsersRepository: JpaRepository<Users, Int> {

    @Query("""
        SELECT CASE
             WHEN COUNT(1) > 0 THEN TRUE
             ELSE FALSE
         END
        FROM Users s
        WHERE s.email = ?1
    """)
    fun selectEmailIsExist(email: String): Boolean

    @Query("""
        SELECT username
        FROM users
        WHERE email = :email
    """, nativeQuery = true)
    fun selectUsernameByEmail(@Param("email") email: String): Optional<String>

    fun findByEmail(email: String): Optional<Users>
}