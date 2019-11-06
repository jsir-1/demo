package com.jsrf.sort;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jsrf
 */
public class SortService {
    /**
     * 快排 时间复杂度就是 O（nlogn）    空间复杂度为O（nlogn）
     *
     * @param a 待排序的数组
     * @param l 数组的左边界(例如，从起始位置开始排序，则l=0)
     * @param r 数组的右边界(例如，排序截至到数组末尾，则r=a.length-1)
     */
    public void quickSort(int a[], int l, int r) {
        if (l < r) {
            int i, j, x;

            i = l;
            j = r;
            x = a[i];
            while (i < j) {
                while (i < j && a[j] > x) {
                    j--; // 从右向左找第一个小于x的数
                }
                if (i < j) {
                    a[i++] = a[j];
                }
                while (i < j && a[i] < x) {
                    i++; // 从左向右找第一个大于x的数
                }
                if (i < j) {
                    a[j--] = a[i];
                }
            }
            a[i] = x;
            /* 递归调用 */
            quickSort(a, l, i - 1);
            /* 递归调用 */
            quickSort(a, i + 1, r);
        }
    }

    /**
     * 选择排序 时间复杂度就是 O（n²）    空间复杂度为O（1）
     *
     * @param a 待排序的数组
     * @param n 数组的长度
     */
    public void selecSort(int a[], int n) {
        int i;        // 有序区的末尾位置
        int j;        // 无序区的起始位置
        int min;    // 无序区中最小元素位置

        for (i = 0; i < n; i++) {
            min = i;

            // 找出"a[i+1] ... a[n-1]"之间的最小元素，并赋值给min。
            for (j = i + 1; j < n; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }

            // 若min!=i，则交换 a[i] 和 a[min]。
            // 交换之后，保证了a[0] ... a[i] 之间的元素是有序的。
            if (min != i) {
                int tmp = a[i];
                a[i] = a[min];
                a[min] = tmp;
            }
        }
    }

    /**
     * 直接插入排序 复杂度为 O（n²）
     *
     * @param a 待排序的数组
     * @param n 数组的长度
     */
    public void insertSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 0; i < n; i++) {
            int value = a[i];
            int j = i - 1;
            while (j-- >= 0) {
                if (a[j] > value) {
                    //移动数据
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            //插入正确的位置
            a[j + 1] = value;
        }
    }

    /**
     * 归并排序
     * https://www.javazhiyin.com/1222.html
     */
    public static void main(String[] args) {
//        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
//        sort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {5, 6, 7, 8, 9};
        List<Integer> integers = mergeSort(arr1, arr2);
        System.out.println(integers);
    }

    public static void sort(int[] arr) {
        int[] temp = new int[arr.length];
        //在排序前，先建好一个长度等于原数组长度的临时数组，
        //避免递归中频繁开辟空间
        sort(arr, 0, arr.length - 1, temp);
    }

    private static void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //左边归并排序，使得左子序列有序
            sort(arr, left, mid, temp);
            //右边归并排序，使得右子序列有序
            sort(arr, mid + 1, right, temp);
            //将两个有序子数组合并操作
            merge(arr, left, mid, right, temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //左序列指针
        int i = left;
        //右序列指针
        int j = mid + 1;
        //临时数组指针
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        //将左边剩余元素填充进temp中
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        //将右序列剩余元素填充进temp中
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

    /**
     * 归并排序
     */
    static List<Integer> mergeSort(int[] arr1, int[] arr2) {
        ArrayList<Integer> list = Lists.newArrayList();
        int m = arr1.length - 1;
        int n = arr2.length - 1;
        int p1 = 0;
        int p2 = 0;
        while (p1 < m && p2 < n) {
            if (arr1[p1] <= arr2[p2]) {
                list.add(arr1[p1++]);
            } else {
                list.add(arr2[p2++]);
            }
        }
        //将左边剩余元素填充进temp中
        while (p1 <= m) {
            list.add(arr1[p1++]);
        }
        //将右序列剩余元素填充进temp中
        while (p2 <= n) {
            list.add(arr2[p2++]);
        }
        return list;
    }
}
