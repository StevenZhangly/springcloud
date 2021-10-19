package com.demo.user.algorithm;

import com.alibaba.fastjson.JSON;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @ClassName MyPreciseShardingAlgorithm
 * @Description: TODO
 * @Author zly
 * @Date 2021/10/18
 **/
@Slf4j
@Component
public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding result for data source or table's name
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        log.info("collection:" + JSON.toJSONString(availableTargetNames) + ",preciseShardingValue:" + JSON.toJSONString(shardingValue));
        /*Object index = shardingValue.getValue().intValue() % availableTargetNames.size();
        for (String dataSourceName : availableTargetNames) {
            if (dataSourceName.endsWith(index + "")) {
                return dataSourceName;
            }
        }
        throw new UnsupportedOperationException();*/
        if(shardingValue.getValue().intValue() < 5){
            return "user_0";
        } else {
            return "user_1";
        }
    }
}
