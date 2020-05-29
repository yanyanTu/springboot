package com.spring.recipes.aopaspectj;

import java.lang.annotation.*;

/**
 * 定义注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
//Target注解决定LoggingRequired注解可以加在哪些成分上，如加在类身上，或者属性身上，或者方法身上等成分
@Retention(RetentionPolicy.RUNTIME)
/**
 Retention注解决定MyAnnotation注解的生命周期引出
 @Retention元注解的讲解：其三种取值：
    RetentionPolicy.SOURCE：Java源文件(.java文件)、
    RetentionPolicy.CLASS：.class文件-、
    RetentionPolicy.RUNTIME：内存中的字节码
**/
@Documented
public @interface LoggingRequired {
}
