/*
package com.demo.user.algorithm;

import io.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

*/
/**
 * @ClassName AtomicLongShardingKeyGenerator
 * @Description: TODO
 * @Author zly
 * @Date 2021/10/19
 **//*

public class AtomicLongShardingKeyGenerator implements ShardingKeyGenerator {

    private AtomicLong atomicLong = new AtomicLong(0);
    private Properties properties = new Properties();

    */
/**
     * Generate key.
     *
     * @return generated key
     *//*

    @Override
    public Comparable<?> generateKey() {
        return atomicLong.incrementAndGet();
    }

    */
/**
     * Get algorithm type.
     *
     * @return type
     *//*

    @Override
    public String getType() {
        return "AtomicLong";
    }

    */
/**
     * Get properties.
     *
     * @return properties of algorithm
     *//*

    @Override
    public Properties getProperties() {
        return properties;
    }

    */
/**
     * Set properties.
     *
     * @param properties properties of algorithm
     *//*

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
*/
