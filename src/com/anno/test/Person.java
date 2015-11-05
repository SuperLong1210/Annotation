package com.anno.test;

public interface Person
{
    public String name();
    public int age();
    @Deprecated     //警告---标注已经过时的方法
    public void sing();
}
