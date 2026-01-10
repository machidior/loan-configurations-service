//package com.machidior.configuration_service.config;
//
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.validation.Validation;
//import jakarta.validation.Validator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//public class ServiceConfig {
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//
//    @Bean
//    public Validator validator() {
//        return Validation.buildDefaultValidatorFactory().getValidator();
//    }
//
//}
