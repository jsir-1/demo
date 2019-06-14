package com.jsrf.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author jsrf
 */
public class TreeService {
    /**
     * 二叉排序树；左边是小于等于，右边是大于根节点。
     *
     * @param root
     * @param data
     * @return
     */
    public static Tree insert(Tree root, int data) {
        if (root == null) {
            //新建树节点
            return new Tree(data);
        } else {
            Tree cur;
            //小的放在左侧
            if (data < root.data) {
                //递归一直到root为空时，调用第一个IF实现新建树节点
                cur = insert(root.treeLeft, data);
                root.treeLeft = cur;
                //大的放在右侧
            } else if (data > root.data) {
                cur = insert(root.treeRight, data);
                root.treeRight = cur;
            }
            return root;
        }
    }

    /**
     * 递归法实现前序遍历，并打印
     *
     * @param root
     */
    public static void preOrder(Tree root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.treeLeft);
        preOrder(root.treeRight);
    }

    /**
     * 递归法实现中序遍历，并打印
     *
     * @param root
     */
    public static void mindiumOrder(Tree root) {
        if (root == null) {
            return;
        }
        //用递归的方法一直找到树的最左侧
        mindiumOrder(root.treeLeft);
        System.out.print(root.data + " ");
        mindiumOrder(root.treeRight);
    }

    /**
     * 递归方法实现后序遍历，并打印
     *
     * @param root
     */
    public static void postOrder(Tree root) {
        if (root == null) {
            return;
        }
        postOrder(root.treeLeft);
        postOrder(root.treeRight);
        System.out.print(root.data + " ");
    }

    /**
     * 对列法实现二叉树广度优先遍历，队列遵循先进先出的规则，适合本方法
     *
     * @param root
     */
    public static void levelOrer(Tree root) {
        //新增队列
        Queue<Tree> queue = new LinkedList<>();

        if (root != null) {
            //将根节点加入队列
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            //取出队列头并且从队列中删除
            Tree cur = queue.poll();
            System.out.print(cur.data + " ");
            if (cur.treeLeft != null) {
                //先将左分支加入队列，之后先输出
                queue.add(cur.treeLeft);
            }
            if (cur.treeRight != null) {
                queue.add(cur.treeRight);
            }
        }
    }

    /**
     * main函数，输入输出，遍历
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数字的个数：");
        int T = sc.nextInt();
        int[] t = new int[T];
        System.out.println("请输入数字，以空格分隔：");
        for (int i = 0; i < T; i++) {
            t[i] = sc.nextInt();
        }

        Tree root = null;
        for (int i = 0; i < T; i++) {
            root = insert(root, t[i]);
        }
        System.out.println("递归先序遍历：");
        preOrder(root);
        System.out.println();
        System.out.println("递归中序遍历：");
        mindiumOrder(root);
        System.out.println();
        System.out.println("递归后序遍历：");
        postOrder(root);
        System.out.println();
        System.out.println("广度优先遍历：");
        levelOrer(root);

    }
}
