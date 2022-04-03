package com.example.demoeventuate.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackages = "io.eventuate",
        entityManagerFactoryRef = "eventuateTransactionManager",
        transactionManagerRef = "eventuateTransactionManager"
)
public class PersistenceEventuateConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.eventuate")
    public DataSourceProperties eventuateDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.eventuate.configuration")
    public DataSource eventuateDataSource() {
        return eventuateDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "eventuateTransactionManager")
    public LocalContainerEntityManagerFactoryBean eventuateEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(eventuateDataSource())
                .packages(new String[]{"io.eventuate.tram", "io.eventuate.events", "io.eventuate.messaging"})
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager eventuateTransactionManager(
            final @Qualifier("eventuateTransactionManager")
                    LocalContainerEntityManagerFactoryBean memberEntityManagerFactory
    ) {
        return new JpaTransactionManager(Objects.requireNonNull(memberEntityManagerFactory.getObject()));
    }

}
