using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms.core.lists {
    class CircularSLinkedList<Item> {
        Node head;
        Node tail;

        internal class Node {

            internal Node next;
            internal Item data;

            public Node(Item _data) {
                data = _data;
                next = null;
            }
        }

        public CircularSLinkedList() {
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

            if (tail == null)
                tail = tmp;

            head = tmp;
            tail.next = head;
        }

        public void insertAfter(Node node, Item data) {
            Node tmp = new Node(data);

            if (node == tail) {
                tail = tmp;
                tail.next = head;
            }

            if (node == null) {
                tmp.next = head;
                head = tmp;

                if (tail == null)
                    tail = head;

                tail.next = head;

                return;
            }

            Node next = node.next;
            node.next = tmp;
            tmp.next = next;
        }

        public void insertLast(Item data) {
            Node node = new Node(data);

            if (head == null) {
                head = tail = node;
                tail.next = head;
                return;
            }

            tail.next = node;
            tail = node;
            tail.next = head;
        }

        public void deleteFirst() {
            if (head != null) {
                head = head.next;
                tail.next = head;
                if (head == null)
                    tail = null;
            }
        }

        public void deleteLast() {
            if (head != null) {
                Node tmp = head;

                if (tmp.next == head) {
                    head = tail = null;
                }

                while (tmp.next != tail) {
                    tmp = tmp.next;
                }

                tmp.next = null;
                tail = tmp;
                tail.next = head;
            }
        }

        public void deleteNode(Node node) {
            if (head != null) {
                Node tmp = head;
                if (tmp == node) {
                    head = tmp.next;

                    if (head == null)
                        tail = null;
                    else
                        tail.next = head;

                    return;
                }

                do {
                    if (tmp.next == node) {
                        tmp.next = node.next;

                        if (tmp.next == head) {
                            tail = tmp;
                            tail.next = head;
                        }

                        return;
                    }
                    tmp = tmp.next;
                } while (tmp != head);
            }
        }

        public void reverseIter() {

        }

        public void reverseRecur() {

        }

        public void print() {
            Console.WriteLine("CircularSLinkedList: ");
            Node tmp = head;
            do {
                Console.Write(tmp.data + " ");
                tmp = tmp.next;
            } while (tmp != head);
            Console.WriteLine();
        }
    }
}
