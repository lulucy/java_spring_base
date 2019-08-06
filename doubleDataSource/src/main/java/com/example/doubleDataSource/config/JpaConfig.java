package com.example.doubleDataSource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "source1EntityManagerFactory",
        transactionManagerRef = "source1TransactionManager",
        basePackages = {
        "com.example.doubleDataSource.repository" })
public class JpaConfig {

    @Autowired
    @Qualifier("source1DataSource")
    private DataSource source1DataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name="source1EntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
        return source1EntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "source1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean source1EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(source1DataSource).properties(getVendorProperties(source1DataSource))
                .packages("com.example.doubleDataSource.entity") // 设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit").build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Primary
    @Bean(name = "source1TransactionManager")
    public PlatformTransactionManager source1TransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(source1EntityManagerFactory(builder).getObject());
    }
}
