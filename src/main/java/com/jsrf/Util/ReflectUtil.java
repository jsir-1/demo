package com.jsrf.Util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflectUtil {
    /**
     * 将实体中有值的属性放到map中
     * @param object
     * @return
     */
    public static Map<String,Object> entryToMap(Object object){
        Map<String,Object> map=new HashMap<>();
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            Object result=null;
            for(Field field: fields) {
                field.setAccessible(true);
                result = field.get(object);
                if(result!=null) {
                    map.put(field.getName(), result);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
