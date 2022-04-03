package com.smu.tes.demo.repository.general

import com.smu.tes.demo.entity.general.SapConfig
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface SapConfigRepository : JpaRepository<SapConfig, Int> {

    fun findByModuleAndVkbur(module: String, vkbur: String): Optional<SapConfig>
}