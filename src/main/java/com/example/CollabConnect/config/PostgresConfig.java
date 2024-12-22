package com.example.CollabConnect.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.CollabConnect.repository.postgres",
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresTransactionManager"
)
public class PostgresConfig {

    @Bean(name = "postgresDataSource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5434/collabConnect")
                .username("postgres")
                .password("Deeptavo@2708")
                .build();
    }

    @Bean(name = "postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postGresEntityManagerFactory(
            @Qualifier("postgresDataSource") DataSource dataSource
    ){
       LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =
               new LocalContainerEntityManagerFactoryBean();

       localContainerEntityManagerFactoryBean.setDataSource(dataSource);
       localContainerEntityManagerFactoryBean.setPackagesToScan("com.example.CollabConnect.entity.postgres");
       localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update"); // Auto-create tables
        jpaProperties.put("hibernate.show_sql", "true"); // Show SQL queries
        jpaProperties.put("hibernate.format_sql", "true"); // Format SQL queries

        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);

        return localContainerEntityManagerFactoryBean;
    }

    @Bean(name = "postgresTransactionManager")
    public JpaTransactionManager jpaTransactionManager(
            @Qualifier("postgresEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            ){
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
}
