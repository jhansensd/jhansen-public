using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms.core.lists {
    class CircularDLinkedList<Item> {
        Node head;
        Node tail;

        internal class Node {

            internal Node next;
            internal Node prev;
            internal Item data;

            public Node(Item _data) {
                data = _data;
                next = null;
                prev = null;
            }
        }

        public CircularDLinkedList() {
            head = null;
            tail = null;
        }

        public Node search(Item data) {
            Node tmp = head;
            do {
                if (tmp.data.Equals(data))
                    return tmp;

                tmp = tmp.next;
            } while (tmp != head);
            return null;
        }

        public void insertFirst(Item data) {
            Node tmp = new Node(data);
            tmp.next = head;

            if (head != null)
                head.prev = tmp;

            if (tail == null) {
                tail = tmp;
            } else if (tail.prev == null) {
                tail.prev = tmp;
            }

            head = tmp;
            head.prev = tail;
            tail.next = head;
        }

        public void insertAfter(Node node, Item data) {
            Node tmp = new Node(data);

            if (node == tail) {
                tail = tmp;
            }

            if (node == null) {
                tmp.next = head;
                tmp.next.prev = tmp;
                head = tmp;

                if (tail == null)
                    tail = head;

                tail.next = head;
                head.prev = tail;

                return;
            }

            Node next = node.next;
            node.next = tmp;
            tmp.next = next;
            next.prev = tmp;
            tmp.prev = node;
        }

        public void insertLast(Item data) {
            Node node = new Node(data);

            if (head == null) {
                head = tail = node;
                head.prev = tail;
                tail.next = head;
                return;
            }

            tail.next = node;
            node.prev = tail;
            tail = node;
            head.prev = tail;
        }

        public void deleteFirst() {
            if (head != null) {
                head = head.next;
                tail.next = head;
                if (head == null)
                    tail = null;
                else
                    head.prev = tail;
            }
        }

        public void deleteLast() {
            if (tail != null) {
                if (tail.prev == null)
                    tail = head = null;
                else {
                    tail.prev.next = null;
                    tail = tail.prev;
                    head.prev = tail;
                    tail.next = head;
                }
            }
        }

        public void deleteNode(Node node) {
            if (node == tail) {
                tail = tail.prev;
                tail.next = head;
                head.prev = tail;
                return;
            }

            if (node == head) {
                head = head.next;
                head.prev = tail;
                tail.next = head;
                return;
            }

            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        public void reverseIter() {

        }

        public void reverseRecur() {

        }

        public void print() {
            Console.WriteLine("CircularDLinkedList: ");
            Node tmp = tail;
            String str = "";
            do {
                str = tmp.data + " " + str;
                tmp = tmp.prev;
            } while (tmp != tail);
            Console.WriteLine(str);
        }
    }
}
