package com.company;

import java.util.Stack;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        com.company.Solution.Table table = new com.company.Solution.Table(n, k);
        for(String s : cmd) {
            table.doCmd(s);
        }
        return table.getAnswer();
    }

    static class Table {
        Stack<com.company.Solution.Node> history = new Stack<>();
        com.company.Solution.Node root = new com.company.Solution.Node(0);
        com.company.Solution.Node tail;
        com.company.Solution.Node cur = root;
        int size;

        public Table(int n, int k) {
            this.size = n;

            com.company.Solution.Node p = root;
            for(int i=1; i<n; i++) {
                com.company.Solution.Node node = new com.company.Solution.Node(i);
                p.next = node;
                node.prev = p;
                p = node;
                if(i == k) {
                    cur = p;
                }
            }
            tail = p;
            root.prev = tail;
            tail.next = root;
        }

        public void remove() {
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
            history.push(cur);
            if(cur == root) {
                root = cur.next;
                cur = root;
            } else if(cur == tail) {
                tail = cur.prev;
                cur = tail;
            }else {
                cur = cur.next;
            }
        }

        public void unDo() {
            com.company.Solution.Node node = history.pop();
            com.company.Solution.Node p = node.prev.next;
            node.prev.next = node;
            p.prev = node;

            if(node.data < root.data) {
                root = node;
            } else if(node.data > tail.data) {
                tail = node;
            }
        }

        public void moveUp(int n) {
            while(n-- > 0) {
                cur = cur.prev;
            }
        }

        public void moveDown(int n) {
            while(n-- > 0) {
                cur = cur.next;
            }
        }

        public void doCmd(String s) {
            String[] v = s.split(" ");

            switch (v[0]) {
                case "U":
                    moveUp(Integer.parseInt(v[1]));
                    break;
                case "D":
                    moveDown(Integer.parseInt(v[1]));
                    break;
                case "C":
                    remove();
                    break;
                case "Z":
                    unDo();
                    break;
                default:
                    break;
            }
        }

        public String getAnswer() {
            StringBuilder sb = new StringBuilder();

            com.company.Solution.Node p = root;
            for(int i=0; i<size; i++) {
                if(p.data == i) {
                    sb.append("O");
                    p = p.next;
                }else {
                    sb.append("X");
                }
            }
            return sb.toString();
        }
    }

    static class Node {
        int data;
        com.company.Solution.Node prev;
        com.company.Solution.Node next;

        public Node(int data) {
            this.data = data;
            prev = null;
            next = null;
        }
    }
}
