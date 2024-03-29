package com.demo.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DataSourceConfig
 * @Description: 读写分离数据源配置
 * @Author zly
 * @Date 2021/10/22
 **/
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(value = "spring.datasource.master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(value = "spring.datasource.slave1")
    public DataSource slave1DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(value = "spring.datasource.slave2")
    public DataSource slave2DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource myRoutingDataSource( DataSource masterDataSource,
                                           DataSource slave1DataSource,
                                           DataSource slave2DataSource){
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.MASTER, masterDataSource);
        targetDataSources.put(DBTypeEnum.SLAVE1, slave1DataSource);
        targetDataSources.put(DBTypeEnum.SLAVE2, slave2DataSource);
        MyRoutingDataSource routingDataSource = new MyRoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(masterDataSource);
        routingDataSource.setTargetDataSources(targetDataSources);
        return routingDataSource;
    }
}
