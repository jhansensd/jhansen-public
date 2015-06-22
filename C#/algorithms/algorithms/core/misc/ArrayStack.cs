using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms.core.misc {
    class ArrayStack<Item> {
        private int top;
        private Item[] elements;

        public ArrayStack(int max) {
            top = -1;
            elements = new Item[max];
        }

        public bool isEmpty() { return top == -1; }
        public bool isFull() { return (top + 1) == elements.Length; }
        public int Size() { return top + 1; }

        public void push(Item item) {
            if (isFull())
                return;

            elements[++top] = item;
        }

        public Item pop() {
            if (isEmpty())
                return default(Item);

            return elements[top--];
        }

        public Item peek() {
            if (isEmpty())
                return default(Item);

            return elements[top];
        }

        public void print() {
            if (isEmpty())
                return;

            String str = "";

            for (int i = 0; i < top + 1; i++) {
                str += elements[i] + " ";
            }
            Console.WriteLine("Printing ArrayStack:" + str);
        }
    }
}
