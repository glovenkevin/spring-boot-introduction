# Spring Boot Project for Introduction

This is my spring boot project with maven and kotlin where the project used simple CRUD using JPA and Hibernate. 

You can check the APIs Documentation on this [link](https://documenter.getpostman.com/view/5831343/UVsPQ5XZ).

This project included dockerfile to build the image where the default dockerfile using multistage build and there is one without multistage. 

In Another hand i included kubernetes deployment config for testing in local environtment. The reason behind using kubernetes is for my learning purpose.

This project connected to my PPT presentation on this [link](https://docs.google.com/presentation/d/1AY2srBH3NSf40DrH-2_ibw36vs5sgj5bgh-Ei8d4ifA/edit?usp=sharing).

## Preparation

If you have docker installed on your computer or laptop, 
you can use it to deploy MySQL or MariaDB container for the database. 
In this project we use 2 database, 1 as our main database and then the other are being our support database which will save some config to use dynamic.

### Main Database
```sql
CREATE TABLE users (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
);
```

### Support Database
```sql
CREATE TABLE m_sap_config (
    id INT UNSIGNED auto_increment NULL,
    user_id varchar(20) NOT NULL,
    password varchar(50) NOT NULL,
    connection_id varchar(30) NOT NULL,
    client varchar(5) NOT NULL,
    `language` varchar(2) NOT NULL,
    host varchar(15) NOT NULL,
    system_number varchar(2) NOT NULL,
    `type` varchar(10) DEFAULT '' NOT NULL,
    min_pool varchar(5) NULL,
    max_pool varchar(5) NULL,
    module varchar(4) NOT NULL,
    vkbur varchar(5) NOT NULL,
    CONSTRAINT m_sap_users_pk PRIMARY KEY (id),
    CONSTRAINT m_sap_users_un UNIQUE KEY (module,vkbur)
);
```

After that put the MySQL/MariaDB url in application.yml. 

## Preparation 2

In this project we use SAP. You need to put SapJCO inside folder lib in root folder.
Don't forget to put de .so (linux) or .dll (windows) file. 
This project using SapJCO3, so if you don't have those file you can comment or remove SapJcoDataSource.

## API Docs

I have included the swagger-ui in this project, 
you can check the API url with the request response data in this path `/api/docs`.

## Reference

- [Link](https://www.baeldung.com/spring-data-jpa-multiple-databases) Baeldung giving explanation about how to config multiple datasource
- [Thread SAP](https://answers.sap.com/answers/8555945/view.html) : Sap Connection maintained by the framework hence we don't have to close the connection after the application being shutdown
- [Link](https://sourceforge.net/p/rcer/git/ci/master/tree/net.sf.rcer.conn/src/net/sf/rcer/conn/connections/ConnectionManager.java#l66) Sourceforge of the code that become my basic implementation for creating DataDestinationProvider for SapJCO Connection
- [Link](https://reflectoring.io/spring-boot-conditionals/) articles for bean conditional implementation