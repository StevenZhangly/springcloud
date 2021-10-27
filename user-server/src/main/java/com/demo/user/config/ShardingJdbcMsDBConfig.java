package com.demo.user.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.jdbc.Driver;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import io.shardingsphere.shardingjdbc.spring.boot.common.SpringBootConfigMapConfigurationProperties;
import io.shardingsphere.shardingjdbc.spring.boot.common.SpringBootPropertiesConfigurationProperties;
import io.shardingsphere.shardingjdbc.spring.boot.masterslave.SpringBootMasterSlaveRuleConfigurationProperties;
import io.shardingsphere.shardingjdbc.spring.boot.sharding.SpringBootShardingRuleConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ShardingJdbcMasterslaveDBConfig
 * @Description: TODO
 * @Author zly
 * @Date 2021/10/26
 **/
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.demo.user.msmapper"}, sqlSessionFactoryRef = "msDbSqlSessionFactory")
@EnableConfigurationProperties({
        SpringBootShardingRuleConfigurationProperties.class, SpringBootMasterSlaveRuleConfigurationProperties.class,
        SpringBootConfigMapConfigurationProperties.class, SpringBootPropertiesConfigurationProperties.class
})
@RequiredArgsConstructor
public class ShardingJdbcMsDBConfig {

    private final SpringBootMasterSlaveRuleConfigurationProperties masterSlaveProperties;

    private final SpringBootConfigMapConfigurationProperties configMapProperties;

    private final SpringBootPropertiesConfigurationProperties propMapProperties;

    @Bean
    public DataSource shardingJdbcMsDataSource() throws SQLException {
        return buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException {
        DataSource masterDataSource = createDataSource("jdbc:mysql://localhost:3306/master-slave-test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai");
        DataSource slaveDataSource1 = createDataSource("jdbc:mysql://localhost:3307/master-slave-test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai");
        DataSource slaveDataSource2 = createDataSource("jdbc:mysql://localhost:3308/master-slave-test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai");
        Map<String, DataSource> dataSourceMap = new HashMap<>(3);
        dataSourceMap.put("master", masterDataSource);
        dataSourceMap.put("slave1", slaveDataSource1);
        dataSourceMap.put("slave2", slaveDataSource2);
        DataSource masterSlaveDs = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, masterSlaveProperties.getMasterSlaveRuleConfiguration(),configMapProperties.getConfigMap(),propMapProperties.getProps());
        return masterSlaveDs;
    }

    @Bean(name = "msDbSqlSessionFactory")
    public SqlSessionFactory MsDbSqlSessionFactory(DataSource shardingJdbcMsDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(shardingJdbcMsDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sqlmap/MsUserMapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource shardingJdbcMsDataSource){
        return new DataSourceTransactionManager(shardingJdbcMsDataSource);
    }

    private static DataSource createDataSource(final String dataSourceUrl) {
        DruidDataSource result = new DruidDataSource();
        result.setDriverClassName(Driver.class.getName());
        result.setUrl(dataSourceUrl);
        result.setUsername("root");
        result.setPassword("123456");
        return result;
    }
}
