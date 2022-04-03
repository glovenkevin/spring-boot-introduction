package com.smu.tes.demo.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.smu.tes.demo.repository.general"],
    entityManagerFactoryRef = "generalEntityManager",
    transactionManagerRef = "generalTransactionManager"
)
class GeneralDatasourceConfiguration {

    @Bean
    fun generalTransactionManager() : PlatformTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = generalEntityManager().`object`
        return transactionManager
    }

    @Bean
    fun generalEntityManager(): LocalContainerEntityManagerFactoryBean {
        val entityManager = LocalContainerEntityManagerFactoryBean()
        entityManager.dataSource = generalDatasource()
        entityManager.setPackagesToScan("com.smu.tes.demo.entity.general")

        val jpaVendorAdapter = HibernateJpaVendorAdapter()
        entityManager.jpaVendorAdapter = jpaVendorAdapter

        return entityManager
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.general-datasource")
    fun generalDatasource(): DataSource {
        return DataSourceBuilder.create().build()
    }

}