package com.demo.user.algorithm;

import io.shardingsphere.core.keygen.KeyGenerator;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName AtomicLongKeyGenerator
 * @Description: TODO
 * @Author zly
 * @Date 2021/10/19
 **/
public class AtomicLongKeyGenerator implements KeyGenerator {

    private AtomicLong atomicLong = new AtomicLong(0);

    /**
     * Generate key.
     *
     * @return generated key
     */
    @Override
    public Number generateKey() {
        return atomicLong.incrementAndGet();
    }
}
