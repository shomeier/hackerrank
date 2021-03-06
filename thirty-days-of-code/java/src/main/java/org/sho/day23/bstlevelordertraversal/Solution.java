// https://www.hackerrank.com/challenges/30-binary-trees/problem
package org.sho.day23.bstlevelordertraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

class Node {
    Node left, right;
    int data;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}


class Solution {

    private static Queue<Node> queue = new LinkedList<>();

    static void levelOrder(Node root) {
        String levelOrder = getLevelOrder(root).stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(levelOrder);
    }

    static List<Integer> getLevelOrder(Node root) {

        List<Integer> retVal = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();

        if (root != null) {
            queue.add(root);

            while (!queue.isEmpty()) {
                Node node = queue.remove();
                retVal.add(node.data);

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return retVal;
    }

    // recursive solution, not very elegant
    static void levelOrderRecursive(Node root) {
        List<Integer> order = new ArrayList<>();
        getLevelOrderRecursive(root, order);
        String levelOrder = order.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(levelOrder);
    }

    static void getLevelOrderRecursive(Node root, List<Integer> order) {

        order.add(root.data);

        if (root.left != null) {
            queue.add(root.left);
        }
        if (root.right != null) {
            queue.add(root.right);
        }

        if (!queue.isEmpty()) {
            getLevelOrderRecursive(queue.remove(), order);
        }
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        Node root = null;
        while (T-- > 0) {
            int data = sc.nextInt();
            root = insert(root, data);
        }
        levelOrder(root);
    }
}
