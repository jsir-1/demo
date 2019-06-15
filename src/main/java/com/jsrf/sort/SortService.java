package com.jsrf.sort;

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
}
