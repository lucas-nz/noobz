package com.zhousz.zerg.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 自定义方法级别注解
@Retention(RetentionPolicy.RUNTIME) // 保留时间, 保留至运行时.
public @interface Log {
	String value() default "";
}
