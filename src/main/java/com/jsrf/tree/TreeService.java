package com.jsrf.tree;

import com.google.common.collect.Lists;

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
    public static TreeNode insert(TreeNode root, int data) {
        if (root == null) {
            //新建树节点
            return new TreeNode(data);
        } else {
            TreeNode cur;
            //小的放在左侧
            if (data < root.data) {
                //递归一直到root为空时，调用第一个IF实现新建树节点
                cur = insert(root.left, data);
                root.left = cur;
                //大的放在右侧
            } else if (data > root.data) {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    /**
     * 递归法实现前序遍历，并打印
     *
     * @param root
     */
    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 递归法实现中序遍历，并打印
     *
     * @param root
     */
    public static void mindiumOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        //用递归的方法一直找到树的最左侧
        mindiumOrder(root.left);
        System.out.print(root.data + " ");
        mindiumOrder(root.right);
    }

    /**
     * 递归方法实现后序遍历，并打印
     *
     * @param root
     */
    public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    /**
     * 对列法实现二叉树广度优先遍历，队列遵循先进先出的规则，适合本方法
     *
     * @param root
     */
    public static void levelOrder(TreeNode root) {
        //新增队列
        Queue<TreeNode> queue = new LinkedList<>();

        if (root != null) {
            //将根节点加入队列
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            //取出队列头并且从队列中删除
            TreeNode cur = queue.poll();
            System.out.print(cur.data + " ");
            if (cur.left != null) {
                //先将左分支加入队列，之后先输出
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
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

        TreeNode root = null;
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
        levelOrder(root);

    }

    /**
     * 获取二叉树的最大深度
     *
     * @param node
     * @return
     */
    public int getMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getMaxDepth(node.getLeft());
        int right = getMaxDepth(node.getRight());
        return Math.max(left, right) + 1;
    }

    /**
     * 获取二叉树的最小深度
     *
     * @param node
     * @return
     */
    public int getMinDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        }
        int left = getMinDepth(node.getLeft());
        int right = getMinDepth(node.getRight());
        return Math.min(left, right) + 1;
    }


    /**
     * 求二叉树中节点的个数
     */
    public int getNodeNum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getNodeNum(node.getLeft());
        int right = getNodeNum(node.getRight());
        return left + right + 1;
    }

    /**
     * 求二叉树中叶子节点的个数
     */
    public int getLeafNodeNum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        }
        return getLeafNodeNum(node.getLeft()) + getLeafNodeNum(node.getRight());
    }

    /**
     * 求二叉树中第k层节点的个数
     * 其实就是第k-1层的子节点
     */
    public int getKLevelNodeNum(TreeNode node, int k) {
        if (node == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int left = getKLevelNodeNum(node.getLeft(), k - 1);
        int right = getKLevelNodeNum(node.getRight(), k - 1);
        return left + right;
    }

    /**
     * 判断二叉树是否是平衡二叉树
     * AVL树:它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树
     */
    public Boolean isAVLTree(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (Math.abs(getMaxDepth(node.getLeft())) - getMaxDepth(node.getRight()) > 1) {
            return false;
        }
        return isAVLTree(node.getLeft()) && isAVLTree(node.getRight());
    }

    /**
     * 判断二叉树是否是完全二叉树
     */
    public Boolean isCompleteTree(TreeNode node) {
        if (node == null) {
            return false;
        }
        Queue<TreeNode> queue = Lists.newLinkedList();
        //根节点进行入队
        queue.add(node);
        //标识位
        Boolean result = true;
        Boolean hasTwoChild = true;
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            if (hasTwoChild) {
                if (current.getLeft() != null && current.getRight() != null) {
                    //入队
                    queue.add(current.getLeft());
                    queue.add(current.getRight());
                } else if (current.getLeft() != null && current.getRight() == null) {
                    queue.add(current.getLeft());
                    hasTwoChild = false;
                } else if (current.getLeft() == null && current.getRight() != null) {
                    result = false;
                    break;
                } else {
                    hasTwoChild = false;
                }
            } else {
                if (current.getLeft() != null || current.getRight() != null) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 判断两颗二叉树是否完全相同
     */
    public Boolean isSameTree(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return Boolean.TRUE;
        } else if (node1 == null || node2 == null) {
            return Boolean.FALSE;
        } else if (node1.getData() != node2.getData()) {
            return Boolean.FALSE;
        }
        Boolean left = isSameTree(node1.getLeft(), node2.getLeft());
        Boolean right = isSameTree(node1.getRight(), node2.getRight());
        return left && right;
    }

    /**
     * 判断两颗二叉树是否互为镜像
     */
    public Boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return Boolean.TRUE;
        }
        if (node1 == null || node2 == null) {
            return Boolean.FALSE;
        }
        if (node1.getData() != node2.getData()) {
            return Boolean.FALSE;
        }
        return isMirror(node1.getLeft(), node2.getRight()) && isMirror(node1.getRight(), node2.getLeft());
    }

    /**
     * 求二叉树的镜像／翻转二叉树（破坏原来的树）
     */
    public TreeNode mirrorTreeDestory(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode left = mirrorTreeDestory(node.getLeft());
        TreeNode right = mirrorTreeDestory(node.getRight());
        node.setLeft(right);
        node.setRight(left);
        return node;
    }

    /**
     * 求二叉树的镜像／翻转二叉树（不破坏原来的树）
     */
    public TreeNode mirrorTreeNoDestory(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode left = mirrorTreeNoDestory(node.getLeft());
        TreeNode right = mirrorTreeNoDestory(node.getRight());

        TreeNode node1 = new TreeNode(node.getData());
        node1.setLeft(right);
        node1.setRight(left);
        return node1;
    }
}
