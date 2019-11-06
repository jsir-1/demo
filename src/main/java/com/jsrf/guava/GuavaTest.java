package com.jsrf.guava;


import com.google.common.base.MoreObjects;
import com.google.common.collect.HashBiMap;

/**
 * @author jsrf
 * @Date 2019-09-30 10:51
 * @Description
 */
public class GuavaTest {
    public static void main(String[] args) {
        //找到第一个非空的对象
        Object o = MoreObjects.firstNonNull("test", 1);
        //Argument was 5
        String format = String.format("Argument was %s", 5);
        HashBiMap<String, String> biMap = HashBiMap.create();
        String a = biMap.put("a", null);
        String s = biMap.inverse().get(null);
        System.out.println(s);
        biMap.put("b", null);
        String t = biMap.inverse().get(null);
        System.out.println(t);
    }


}
