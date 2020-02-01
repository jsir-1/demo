package com.jsrf.jvm;

import java.util.HashMap;

public class DemoTest {
    //    private int a;
//    private String string;
    private boolean flag;

    public static void main(String[] args) {
        Integer a = new Integer(355);
        Integer c = new Integer(355);
        Long b = 5L;
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

        HashMap<Integer, Object> map = new HashMap<>();
        map.put(a, "fsdgs");
        Object o = map.get(c);
        System.out.println(o);
    }
}
