package com.jsrf.jvm;

/**
 * char int long  float double  Character  Serializable object char...
 */
public class OverLoad {

//    public static void sayHello(char arg) {
//        System.out.println("char");
//    }

//    public static void sayHello(int arg) {
//        System.out.println("int");
//    }

//    public static void sayHello(long arg) {
//        System.out.println("long");
//    }

//    public static void sayHello(float arg) {
//        System.out.println("float");
//    }

//    public static void sayHello(double arg) {
//        System.out.println("double");
//    }

//    public static void sayHello(Character arg) {
//        System.out.println("Character");
//    }


//    public static void sayHello(Serializable arg) {
//        System.out.println("Serializable");
//    }

//    public static void sayHello(Object object) {
//        System.out.println("object");
//    }


    public static void sayHello(char... arg) {
        System.out.println("char...");
    }

    /**
     * 不能大转小
     *
     * @param args
     */
//    public static void sayHello(byte arg) {
//        System.out.println("byte");
//    }
    public static void main(String[] args) {
        sayHello('a');
    }
}
