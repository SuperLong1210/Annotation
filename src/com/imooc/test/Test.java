package com.imooc.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test
{
    public static void main(String[] args)
    {
        Filter f1=new Filter();
        f1.setId(10);
        Filter f2=new Filter();
        f2.setUsername("Test");
        f2.setAge(11);
        Filter f3=new Filter();
        f3.setEmail("guoke881210@126.com,164425286@qq.com");
        
        String sql1=query(f1);
        String sql2=query(f2);
        String sql3=query(f3);
        
        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);
        
        Filter2 t2=new Filter2();
        t2.setName("wanglong");
        t2.setAmount(11);
        System.out.println(query(t2));
    }

    private static String query(Object f)
    {
        StringBuffer sb=new StringBuffer();
        //1.获取到Class
        Class<? extends Object> c=f.getClass();
        //2.获取到Table的名字
        boolean exists=c.isAnnotationPresent(Table.class);
        if(!exists){
            return null;
        }
        Table t=(Table) c.getAnnotation(Table.class);
        String tableName=t.value();
        sb.append("SELECT * FROM ").append(tableName).append(" WHERE 1=1");
        //3.遍历所有字段
        Field[] fArray=c.getDeclaredFields();
        for(Field field:fArray){
            //4.处理每个字段对应的sql
            //4.1 拿到字段名
            boolean fExists=field.isAnnotationPresent(Column.class);
            if(!fExists){
                continue;
            }
//            Column column=field.getAnnotation(Column.class);
//            String columnName=column.value();
            //4.2 拿到字段的值
            String fieldName=field.getName();
            String getMethodName="get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
            
            Object fieldValue=null;
            try
            {
                Method getMethod=(Method)c.getMethod(getMethodName);
                fieldValue=(Object)getMethod.invoke(f);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            //4.3 拼裝sql
            if(fieldValue==null||(fieldValue instanceof Integer&&(Integer)fieldValue==0)){
                continue;
            }
            sb.append(" and ").append(fieldName);
            if(fieldValue instanceof String){
                if(((String)fieldValue).contains(",")){
                    String[] values=((String)fieldValue).split(",");
                    sb.append(" in(");
                    for(String str:values){
                        sb.append("'").append(str).append("'").append(",");
                    }
                    sb.deleteCharAt(sb.length()-1);
                    sb.append(")");
                }else{
                    sb.append("=").append("'").append(fieldValue).append("'");
                }
            }else{
                sb.append("=").append(fieldValue);
            }
        }
        return sb.toString();
    }
}   
