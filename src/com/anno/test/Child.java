package com.anno.test;

@Description("I am class annotation")
public class Child implements Person
{
    
    @Override
    @Description("I am method annotation")
    public String name()
    {
        return null;
    }
    
    @Override
    public int age()
    {
        return 0;
    }
    
    @Override
    public void sing()
    {
        
    }
    
}
