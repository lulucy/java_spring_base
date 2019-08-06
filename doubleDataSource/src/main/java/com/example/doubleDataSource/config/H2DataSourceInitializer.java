package com.example.doubleDataSource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class H2DataSourceInitializer {
    /**
     * 构建Resource对象
     */
    @Value("classpath:schema_h2.sql")
    private Resource businessScript;

    @Value("classpath:data_h2.sql")
    private Resource dataScript;

    /**
     * 自定义Bean实现业务的特殊需求
     * @param dataSource
     * @return
     */

    @Bean(name = "h2dataSourceInitializer")
    public DataSourceInitializer h2dataSourceInitializer(@Qualifier("source2DataSource") DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        // 设置数据源
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(businessScript);
        populator.addScripts(dataScript);
        return populator;
    }
}
