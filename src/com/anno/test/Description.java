package com.anno.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})      //注解的作用域
@Retention(RetentionPolicy.RUNTIME)     //生命周期
@Inherited      //允许子类继承
@Documented     //生成javadoc时会包含注解
public @interface Description
{
//    String Desc();  
//    //必须无参数、无异常，可以有默认值
//    //合法的类型包括原始类型及String,Class,Annotation,Enumeration
//    //如果注解只有一个成员，则成员名必须取名为value()，在使用时可以忽略成员名和赋值号(=)
//    String Author();
//    int age() default 18;

    String value();
}
