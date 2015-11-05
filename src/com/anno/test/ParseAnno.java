package com.anno.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ParseAnno
{
    public static void main(String[] args)
    {
        try
        {
            //1.使用类加载器加载类
            Class<?> c=Class.forName("com.anno.test.Child");
            //2.找到类上面的注解
            boolean isExist=c.isAnnotationPresent(Description.class);
            if(isExist){
                //3.打印注解
                Description d=(Description)c.getAnnotation(Description.class);
                System.out.println("1=="+d.value());
            }
            Method[] ms=c.getMethods();
            for(Method m:ms){
                boolean isMExist=m.isAnnotationPresent(Description.class);
                if(isMExist){
                    Description d=m.getAnnotation(Description.class);
                    System.out.println("2=="+d.value());
                }
            }
            for(Method m:ms){
                Annotation[] as= m.getAnnotations();
                for(Annotation a :as){
                    if(a instanceof Description){
                        Description d=(Description)a;
                        System.out.println("3=="+d.value());
                    }
                }
                
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        
    }
}
