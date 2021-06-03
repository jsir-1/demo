package com.jsrf.stream;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Desc:请输入类描述信息
 * ----------------------------------
 *
 * @author:jishirifang@meituan.com
 * @Time:2020/5/26 9:46 上午
 */
public class Test {

    public static void main(String[] args) {
        //对每一个原数进行stream操作
        Stream.of("a-b-c-d", "e-f-i-g-h")
                .flatMap(e -> Stream.of(e.split("-")))
                .forEach(System.out::println);


        User w = new User("w", 10);
        User x = new User("x", 11);
        User y = new User("y", 12);

        Stream.of(w, x, y)
                .peek(e -> e.setName(e.getAge() + e.getName())) //一般用于debug
                .forEach(e -> System.out.println(e.toString()));


        Stream.of(w, x, y)
                .sorted(Comparator.comparing(e -> e.age, (o1, o2) -> o1 > o2 ? 1 : o1.equals(o2) ? 0 : -1))
                .forEach(e -> System.out.println(e.toString()));

    }


    static class User {

        private String name;

        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
