package com.jsrf.api.learn;

import java.util.BitSet;

/**
 * https://www.jianshu.com/p/4fbad3a6d253
 */
public class BitSetLearn {
    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(1 << 0));
//        System.out.println(Integer.toBinaryString(1 << 1));
//        System.out.println(Integer.toBinaryString(1 << 2));
//        System.out.println(Integer.toBinaryString(1 << 3));
//        System.out.println(Integer.toBinaryString(1 << 4));
//        System.out.println(Integer.toBinaryString(1 << 5));
//        System.out.println(Integer.toBinaryString(1 << 6));
//        System.out.println(Integer.toBinaryString(1 << 7));

        BitSet bitSet = new BitSet(1024);

        Integer[] arr = {1,2,3,4,5,6,8,3,1,4,6,9,10};
        for (Integer integer : arr) {
            bitSet.set(integer);
        }
        for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i+1)) {
            System.out.println(i);
        }

    }

}
