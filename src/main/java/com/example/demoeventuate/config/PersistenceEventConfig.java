package com.example.demoeventuate.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.demoeventuate",
        entityManagerFactoryRef = "eventEntityManager",
        transactionManagerRef = "eventTransactionManager"
)
public class PersistenceEventConfig {

    @Bean
//    @Primary
    @ConfigurationProperties("spring.datasource.event")
    public DataSourceProperties eventDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
//    @Primary
    @ConfigurationProperties("spring.datasource.event.configuration")
    public DataSource eventDataSource() {
        return eventDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    //    @Primary
    @Bean(name = "eventEntityManager")
    public LocalContainerEntityManagerFactoryBean eventEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(eventDataSource())
                .packages(new String[]{"com.example.demoeventuate"})
                .build();
    }

    //    @Primary
    @Bean
    public PlatformTransactionManager eventTransactionManager(
            final @Qualifier("eventEntityManager")
                    LocalContainerEntityManagerFactoryBean memberEntityManagerFactory
    ) {
        return new JpaTransactionManager(Objects.requireNonNull(memberEntityManagerFactory.getObject()));
    }

}
