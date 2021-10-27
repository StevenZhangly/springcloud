package com.demo.user.config;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 强制路由主库
 */
@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface MasterRouteOnly {
}
