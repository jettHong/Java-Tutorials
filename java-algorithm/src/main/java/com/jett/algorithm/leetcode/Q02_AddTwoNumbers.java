package com.jett.algorithm.leetcode;

import java.util.Optional;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 */
public class Q02_AddTwoNumbers {
    
    static class ListNode {
        int val;
        ListNode next = null;
        
        ListNode(int x) {
            val = x;
        }
        
        public ListNode nextNode(ListNode next) {
            this.next = next;
            return this;
        }
    }
    
    public static void main(String[] args) {
        
        System.out.println(9 / 10);
        System.out.println(9 % 10);
        System.out.println(0 / 10);
        System.out.println(0 % 10);
        System.out.println(12 / 10);
        System.out.println(12 % 10);
        System.out.println(19 / 10);
        System.out.println(19 % 10);
        System.out.println("--------------");
        
        ListNode l1 = (new ListNode(1)).nextNode(new ListNode(2).nextNode(new ListNode(3).nextNode(new ListNode(0).nextNode(new ListNode(1)))));
        ListNode l2 = (new ListNode(9)).nextNode(new ListNode(8));
        ListNode result = addTwoNumbers(l1, l2);
        ListNode node = result;
        do {
            System.out.println(node.val);
            node = node.next;
        } while (node != null);
    }
    
    /**
     *
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return calc(l1, l2, 0);
    }
    
    public static ListNode calc(ListNode l1, ListNode l2, int add) {
        if (l1 != null || l2 != null || add != 0) {
            ListNode listNode1 = l1 == null ? new ListNode(0) : l1;
            ListNode listNode2 = l2 == null ? new ListNode(0) : l2;
            return new ListNode((listNode1.val + listNode2.val + add) % 10).nextNode(calc(listNode1.next, listNode2.next, (listNode1.val + listNode2.val + add) / 10));
        } else {
            return null;
        }
        
    }
}
