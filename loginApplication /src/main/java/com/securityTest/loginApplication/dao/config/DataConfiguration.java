package com.securityTest.loginApplication.dao.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataConfiguration {


    @Value("${spring.datasource.driverClassName}")
    private String dataSourceClassName;


    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUserName;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

/*
    @Value("${spring.dataspource.hibernate.dialect}")
    private String hibernateDialect;


*/

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;




    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        try {
            LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
            localContainerEntityManagerFactoryBean.setDataSource(dataSource());
            localContainerEntityManagerFactoryBean.setPackagesToScan("com.securityTest.loginApplication.entity");
            localContainerEntityManagerFactoryBean.setPersistenceUnitName("dw");
            HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

            jpaVendorAdapter.setDatabasePlatform(hibernateDialect);
            localContainerEntityManagerFactoryBean.setJpaProperties(hibernateProperties());
            localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
            return localContainerEntityManagerFactoryBean;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception is :");
        }
        return new LocalContainerEntityManagerFactoryBean();
    }


    @Bean
    public DataSource dataSource() {

        final HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(20);
        ds.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        ds.addDataSourceProperty("url", dataSourceUrl);
        ds.addDataSourceProperty("user", dataSourceUserName);
        ds.addDataSourceProperty("password", dataSourcePassword);
        ds.setLeakDetectionThreshold(5000);
        return ds;
    }


    private Properties hibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.dialect", hibernateDialect);
        prop.put("hibernate.show_sql", "true");
        prop.setProperty("hibernate.hbm2ddl.auto", "update");

        return prop;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(
            EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }


}
