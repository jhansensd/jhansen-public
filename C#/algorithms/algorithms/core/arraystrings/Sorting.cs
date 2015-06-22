using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms.core.arraystrings {
    class Sorting {

        public static void bubbleSort(int[] arr ) {
            for (int i = 0; i < arr.Length - 1; i++) {
                for (int j = 1; j < arr.Length - i; j++) {
                    if (arr[j] < arr[j - 1])
                        swap(arr, j, j - 1);
                }
            }
        }

        public static void insertionSort(int[] arr ) {
            for (int i = 1; i < arr.Length; i++) {
                for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                    swap(arr, j, j - 1);
                }
            }
        }

        public static void selectionSort(int[] arr ) {
            int min;
            for (int i = 0; i < arr.Length; i++) {
                min = i;
                for (int j = i + 1; j < arr.Length; j++) {
                    if (arr[j] < arr[min])
                        min = j;
                }
                swap(arr, i, min);
            }
        }

        public static void radixSort(int[] arr ) {
            int i, m = arr[0], exp = 1, n = arr.Length;
            int[] b = new int[10];
            for (i = 1; i < n; i++) {
                if (arr[i] > m)
                    m = arr[i];
            }

            while ((m / exp) > 0) {
                int[] bucket = new int[10];

                for (i = 0; i < n; i++)
                    bucket[(arr[i] / exp) % 10]++;
                for (i = 1; i < 10; i++)
                    bucket[i] += bucket[i - 1];
                for (i = n - 1; i >= 0; i--)
                    b[--bucket[(arr[i] / exp) % 10]] = arr[i];
                for (i = 0; i < n; i++)
                    arr[i] = b[i];
                exp *= 10;
            }
        }

        /*public static void heapSort(Integer arr[] ) {

            BinaryMinHeap<Integer> heap = new BinaryMinHeap<Integer>(arr);

            for (int i = 0; i < arr.length; i++) {
                arr[i] = heap.delMin();
            }
        }*/

        public static int partition(int[] arr, int left, int right) {
            int pivot = arr[(left + right) / 2]; // Pick a pivot point. Can be an element.

            while (left <= right) { // Until we've gone through the whole array
                                    // Find element on left that should be on right
                while (arr[left] < pivot) {
                    left++;
                }

                // Find element on right that should be on left
                while (arr[right] > pivot) {
                    right--;
                }

                // Swap elements, and move left and right indices
                if (left <= right) {
                    swap(arr, left, right);
                    left++;
                    right--;
                }
            }
            return left;
        }

        public static void quickSort(int[] arr, int left, int right) {
            if (left < right) {
                int pivot = partition(arr, left, right);
                quickSort(arr, left, pivot - 1);
                quickSort(arr, pivot, right);
            }
        }

        public static void mergeSort(int[] arr, int low, int high) {
            if (low < high) {
                int middle = (low + high) / 2;
                mergeSort(arr, low, middle);
                mergeSort(arr, middle + 1, high);
                merge(arr, low, middle, high);
            }
        }

        private static void merge(int[] arr, int low, int middle, int high) {

            int[] helper = (int[])arr.Clone();

            int helperLeft = low;
            int helperRight = middle + 1;
            int current = low;

            /* Iterate through helper array. Compare the left and right
             * half, copying back the smaller element from the two halves
             * into the original array. */
            while (helperLeft <= middle && helperRight <= high) {
                if (helper[helperLeft] <= helper[helperRight]) {
                    arr[current] = helper[helperLeft];
                    helperLeft++;
                } else { // If right element is smaller than left element
                    arr[current] = helper[helperRight];
                    helperRight++;
                }
                current++;
            }

            /* Copy the rest of the left side of the array into the
             * target array */
            int remaining = middle - helperLeft;
            for (int i = 0; i <= remaining; i++) {
                arr[current + i] = helper[helperLeft + i];
            }
        }

        private static void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

    }
}
