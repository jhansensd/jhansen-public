using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms {
    class ArrayStrings {
        public const int ASCII_COUNT = 256;

        public static int BinarySearchIter(int[] arr, int val, int low, int high) {
            int mid;
            while (low <= high) {
                mid = (low + high) / 2;
                if (val == arr[mid])
                    return mid;
                else if (val < arr[mid])
                    high = mid - 1;
                else if (val > arr[mid])
                    low = mid + 1;
            }

            return -1;
        }

        public static int BinarySearchRecur(int[] arr, int val, int low, int high) {
            if (low > high)
                return -1;

            int mid = (low + high) / 2;

            if (val == arr[mid])
                return mid;
            else if (val < arr[mid])
                return BinarySearchRecur(arr, val, low, mid - 1);
            else if (val > arr[mid])
                return BinarySearchRecur(arr, val, mid + 1, high);

            return -1;
        }

        public static bool isAnagram1(String s1, String s2) {
            if (s1.Count() != s2.Count())
                return false;

            int[] count = new int[ASCII_COUNT];

            foreach(char c in s1.ToCharArray()) {
                ++count[c];
            }

            foreach(char c in s2.ToCharArray()) {
                if (--count[c] < 0)
                    return false;
            }

            return true;
        }

        public static bool isAnagram2(String s1, String s2) {
            if (s1.Count() != s2.Count())
                return false;

            int[] count = new int[ASCII_COUNT];

            for (int i = 0; i < s1.Count(); i++) {
                ++count[s1.ElementAt(i)];
                --count[s2.ElementAt(i)];
            }

            for (int i = 0; i < s1.Count(); i++) {
                if (count[s1.ElementAt(i)] != 0)
                    return false;
            }

            return true;
        }
    }
}
