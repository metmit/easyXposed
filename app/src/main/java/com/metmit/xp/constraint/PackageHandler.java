package com.metmit.xp.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PackageHandler {
    /**
     * 包名，如：com.ss.android.ugc.aweme、com.smile.gifmaker
     */
    String name() default "";

    /**
     * 别名，如：抖音、快手
     */
    String alias() default "";
}
