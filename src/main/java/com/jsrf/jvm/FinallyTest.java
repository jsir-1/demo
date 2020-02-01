package com.jsrf.jvm;

public class FinallyTest {
    public static void main(String[] args) {
        int i = inc();
        System.out.println(i);
        System.out.println(JVMInterface.i);
    }

    private static int inc() {
        int x = 0;
        try {
            x = 1;
//            int  a = 1/0;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }
}
