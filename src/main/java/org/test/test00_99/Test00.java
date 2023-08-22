package org.test.test00_99;

import java.util.stream.Stream;

/**
 * 双向链表的实现已经反转双向链表
 */
public class Test00 {
    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        Stream.iterate(0, i -> i + 1).limit(10).forEach(list::add);
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list);
        list.reverse();
        System.out.println(list);
    }
}


//双向链表
class DoubleLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public DoubleLinkedList() {
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void add(int data) {
        Node node = new Node(data);
        Node prev = tail.prev;
        prev.next = node;
        node.prev = prev;
        node.next = tail;
        tail.prev = node;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is illegal");
        }
        Node prev = getPrev(index);
        Node next = prev.next.next;
        prev.next = next;
        next.prev = prev;
        size--;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is illegal");
        }
        return getPrev(index).next.data;
    }

    public void set(int index, int data) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is illegal");
        }
        getPrev(index).next.data = data;
    }

    public int size() {
        return size;
    }

    private Node getPrev(int index) {
        Node cur = head;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public Node getFirst(){
        return head.next;
    }

    public Node getLast(){
        return tail.prev;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node cur = head.next;
        while (cur != tail) {
            sb.append(cur.data).append(",");
            cur = cur.next;
        }
        if (sb.lastIndexOf(",") >= 0) {
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        sb.append("]");
        return sb.toString();
    }

    private class Node {
        private int data;
        private Node prev;
        private Node next;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    // 反转双向链表
    // head-1-2-3-tail
    // =>tempHead-tail
    // =>tempHead-1-tail
    // =>tempHead-2-1-tail
    // =>tempHead-3-2-1-tail
    // =>head-3-2-1-tail
    public void reverse() {
        if (head.next == tail) {
            return;
        }
        //临时头节点
        Node tempHead = new Node();
        tempHead.next = tail;
        //当前操作的节点
        Node cur = head.next;
        //下一个操作的节点
        Node next;
        while (cur != tail) {
            //记录下一个操作的节点
            next = cur.next;

            //处理当前节点的下一个节点
            cur.next = tempHead.next;
            tempHead.next.prev = cur;

            //处理当前节点的上一个节点（与头节点绑定）
            tempHead.next = cur;
            cur.prev = tempHead;

            //当前节点指向下一个
            cur = next;
        }

        //将头节点重新指向第一个元素（第一个有值的元素）
        head.next = tempHead.next;
        tempHead.next.prev = head;
    }
}