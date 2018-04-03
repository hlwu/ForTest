package fortest.hlwu.com.fortest.linkedlist;

import android.util.Log;

/**
 * Created by hlwu on 4/2/18.
 */

public class MyLinkedList {
    private Node head;

    private class Node {
        private String data;
        private Node next;
        private Node before;
        public Node(String d, Node n, Node b) {
            data = d;
            next = n;
            before = b;
        }
    }

    public void addNodeAtLast(String data) {
        Node node = new Node(data, null, null);
        if (head == null) {
            head = node;
        } else {
            Node tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = node;
            node.before = tmp;
        }
    }

    public void removeLastNode() {
        Node tmp = head;
        if (tmp == null) {
            Log.d("flaggg", "empty linked list!");
            return;
        }
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.before.next = null;
    }

    public void insertNodeAt(int index, String data) {
        Node node = new Node(data, null, null);
        if (index == 0) {
            node.next = head;
            head.before = node;
            head = node;
            return;
        }
        if (index < 0) {
            Log.d("flaggg", "invalidate params");
            return;
        }
        Node tmp = head;
        int i = 0;
        while (tmp.next != null) {
            if (i == index) {
                break;
            }
            tmp = tmp.next;
            i++;
        }
        if (i < index) {
            Log.d("flaggg", "index is too large, add node at last");
            tmp.next = node;
            node.before = tmp;
        } else if (i > index) {
            Log.d("flaggg", "something wrong");
        } else {
            node.next = tmp;
            node.before = tmp.before;
            tmp.before.next = node;
            tmp.before = node;
        }
    }

    public void deleteNodeAt(int index) {
        if (index < 0) {
            Log.d("flaggg", "invalidate params");
            return;
        }
        if (index == 0) {
            if (head.next != null) {
                head.next.before = null;
                head = head.next;
            } else {
                head = null;
            }
            return;
        }
        int i = 0;
        Node tmp = head;
        while (tmp.next != null) {
            if (i == index) {
                break;
            }
            tmp = tmp.next;
            i++;
        }
        if (i != index) {
            Log.d("flaggg", "something wrong");
        }
        tmp.before.next = tmp.next;
        tmp.next.before = tmp.before;
    }

    public void printList() {
        Node tmp = head;
        while (tmp != null) {
            Log.d("flaggg", "data: " + tmp.data);
            tmp = tmp.next;
        }
    }

}
