package com.anno.test;

public class Test
{
    @SuppressWarnings("deprecation")        //忽略警告
    public void sing(){
        Person p=new Child();
        p.sing();
    }
}   
