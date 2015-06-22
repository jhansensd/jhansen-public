using System;
using System.Diagnostics;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms.core.misc {
    class BinaryMinHeap<Key> : Comparer<Key> {
        private Key[] elements;
        private int size;

        public BinaryMinHeap(int max) {
            elements = new Key[max + 1];
            size = 0;
        }

        public BinaryMinHeap(Key[] keys ) {
            size = keys.Length;
            elements = new Key[keys.Length + 1];
            for (int i = 0; i < size; i++)
                elements[i + 1] = keys[i];

            for (int k = size / 2; k >= 1; k--)
                sink(k);

            Debug.Assert(isMinHeap(1));
        }

        public bool isEmpty() { return size == 0; }
        public bool isFull() { return size == elements.Length; }
        public int Size { get; set; }

        public Key min() {
            if (!isEmpty())
                return elements[1];

            return default(Key);
        }

        public void insert(Key x) {
            if (isFull())
                return;

            elements[++size] = x;
            swim(size);
            Debug.Assert(isMinHeap(1));
        }

        public Key delMin() {
            if (isEmpty())
                return default(Key);

            swap(1, size);
            Key min = elements[size--];
            sink(1);
            elements[size + 1] = default(Key);

            Debug.Assert(isMinHeap(1));
            return min;
        }

        private void swim(int k) {
            while (k > 1 && greater(k / 2, k)) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && greater(j, j + 1)) j++;
                if (!greater(k, j)) break;
                swap(k, j);
                k = j;
            }
        }

        public void swap(int i, int j) {
            Key temp = elements[i];
            elements[i] = elements[j];
            elements[j] = temp;
        }

        private bool greater(int i, int j) {
            return Compare(elements[i], elements[j]) > 0;
        }

        public override int Compare(Key x, Key y) {
            return Comparer<Key>.Default.Compare(x, y);
        }

        private bool isMinHeap(int k) {
            if (k > size) return true;
            int left = 2 * k, right = 2 * k + 1;
            if (left <= size && greater(k, left)) return false;
            if (right <= size && greater(k, right)) return false;
            return isMinHeap(left) && isMinHeap(right);
        }

        public void print() {
            if (isEmpty())
                return;

            Console.WriteLine("Printing Min Binary Heap.");

            for (int i = 1; i <= size; i++) {
                Console.Write(elements[i] + " ");
            }
            Console.WriteLine();
        }
    }
}
