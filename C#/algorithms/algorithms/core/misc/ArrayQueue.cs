using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms.core.misc {
    class ArrayQueue<Item> {
        int front;
        int back;
        int size;
        Item[] elements;

        public ArrayQueue(int max) {
            size = 0;
            front = back = -1;
            elements = new Item[max];
        }

        public bool isEmpty() { return size == 0; }
        public bool isFull() { return size == elements.Length; }
        public int Size { get; set; }

        public void enqueue(Item data) {
            if (isFull())
                return;

            if (isEmpty()) {
                ++front;
                ++back;
            } else {
                back = (back + 1) % elements.Length;
            }

            ++size;
            elements[back] = data;
        }

        public Item dequeue() {
            if (isEmpty())
                return default(Item);

            Item item = elements[front];
            if (front == back)
                front = back = -1;
            else
                front = (front + 1) % elements.Length;

            --size;
            return item;
        }

        public Item peek() {
            if (isEmpty())
                return default(Item);

            return elements[front];
        }

        public void print() {
            if (isEmpty())
                return;

            String str = "";

            int i = front;
            while (front != back) {
                str += elements[i] + " ";
                front = (front + 1) % elements.Length;
            }

            str += elements[back] + " ";
            Console.WriteLine("Printing ArrayQueue:" + str);
        }
    }
}
