using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms.core.misc {
    class ListQueue<Item> {
        ListNode<Item> front;
        ListNode<Item> back;
        int size;

        public ListQueue() {
            front = back = null;
            size = 0;
        }

        public bool isEmpty() { return front == null; }
        public int Size { get; set; }

        public Item peek() {
            if (!isEmpty())
                return default(Item);

            return front.Data;
        }

        public void enqueue(Item item) {
            ListNode<Item> node = new ListNode<Item>(item, null);

            if (isEmpty())
                front = node;
            else
                back.Next = node;

            back = node;
            ++size;
        }

        public Item dequeue() {
            if (isEmpty())
                return default(Item);

            Item item = front.Data;
            front = front.Next;
            --size;

            if (isEmpty())
                back = null;

            return item;
        }

        public void print() {
            if (isEmpty())
                return;

            String str = "";

            foreach(Item data in front) {
                str += data + " ";
            }
            Console.WriteLine("Printing ListQueue:" + str);
        }
    }
}