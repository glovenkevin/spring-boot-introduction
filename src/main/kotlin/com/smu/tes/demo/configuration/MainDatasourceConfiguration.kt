package com.smu.tes.demo.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.smu.tes.demo.repository.main"],
    entityManagerFactoryRef = "mainEntityManager",
    transactionManagerRef = "mainTransactionManager"
)
class MainDatasourceConfiguration {

    @Primary
    @Bean
    fun mainTransactionManager() : PlatformTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = mainEntityManager().`object`
        return transactionManager
    }

    @Primary
    @Bean
    fun mainEntityManager(): LocalContainerEntityManagerFactoryBean {
        val entityManager = LocalContainerEntityManagerFactoryBean()
        entityManager.dataSource = mainDatasource()
        entityManager.setPackagesToScan("com.smu.tes.demo.entity.main")

        val jpaVendorAdapter = HibernateJpaVendorAdapter()
        entityManager.jpaVendorAdapter = jpaVendorAdapter

        return entityManager
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    fun mainDatasource(): DataSource {
        return DataSourceBuilder.create().build()
    }

}