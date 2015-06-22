using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms.core.misc {
    class ListStack<Item> {
        private int size;
        private ListNode<Item> head;

        public ListStack() {
            size = 0;
            head = null;
        }

        public bool isEmpty() { return size == 0; }
        public int Size { get; set; }

        public void push(Item item) {
            ListNode<Item> node = new ListNode<Item>(item, head);
            head = node;
            ++size;
        }

        public Item pop() {
            if (isEmpty())
                return default(Item);

            Item item = head.Data;
            head = head.Next;
            --size;
            return item;
        }

        public Item peek() {
            if (isEmpty())
                return default(Item);

            return head.Data;
        }

        public void print() {
            if (isEmpty())
                return;

            String str = "";

            foreach(Item data in head) {
                str += data + " ";
            }
            Console.WriteLine("Printing ListStack:" + str);
        }
    }
}
