package com.example.doubleDataSource.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
@Slf4j
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "source1DataSource")
    @ConfigurationProperties(prefix = "source1.datasource")
    public DataSource source1DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "source1DataSourceProperties")
    @ConfigurationProperties("source1.datasource")
    public DataSourceProperties source1DataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Resource
    public PlatformTransactionManager source1TxManager(DataSource source1DataSource){
        return new DataSourceTransactionManager(source1DataSource);
    }

    @Bean(name="source1JdbcTemplate")
    public JdbcTemplate source1JdbcTemplate(@Qualifier("source1DataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }


    @Bean
    @ConfigurationProperties("source2.datasource")
    public DataSourceProperties source2DataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean(name = "source2DataSource")
    public DataSource source2DataSource(){
        DataSourceProperties dataSourceProperties = source2DataSourceProperties();
        log.info("source2:{}",dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "source2TxManager")
    @Resource
    public PlatformTransactionManager source2TxManager(@Qualifier("source2DataSource") DataSource source2DataSource){
        return new DataSourceTransactionManager(source2DataSource);
    }

    @Bean(name="source2JdbcTemplate")
    public JdbcTemplate source2JdbcTemplate(@Qualifier("source2DataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
