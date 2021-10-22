package com.demo.user.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName MyRoutingDataSource
 * @Description: TODO
 * @Author zly
 * @Date 2021/10/22
 **/
public class MyRoutingDataSource extends AbstractRoutingDataSource {


    /**
     * Determine the current lookup key. This will typically be
     * implemented to check a thread-bound transaction context.
     * <p>Allows for arbitrary keys. The returned key needs
     * to match the stored lookup key type, as resolved by the
     * {@link #resolveSpecifiedLookupKey} method.
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
