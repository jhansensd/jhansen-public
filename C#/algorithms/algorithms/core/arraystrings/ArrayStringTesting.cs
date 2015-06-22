using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms.core.arraystrings {
    class ArrayStringTesting {
        public static void run( String input ) {
            /*int search = 224;
            int[] arr = { 11, 23, 99, 132, 224, 564, 756, 1122, 1433, 1655 };
            int found = ArrayStrings.BinarySearchIter(arr.ToArray(), search, 0, arr.Length - 1);
            Console.WriteLine("BinarySearchIter of {0} is {1}.", search, found);
            found = ArrayStrings.BinarySearchRecur(arr.ToArray(), search, 0, arr.Length - 1);
            Console.WriteLine("BinarySearchRecur of {0} is {1}.", search, found);

            // Anagrams.
            String s1 = "abcdefg";
            String s2 = "bacdefg";
            String s3 = "gaedcfb";
            String s4 = "bacdwfg";
            Console.WriteLine("{0} and {1} IsAnagram1(): {2}", s1, s2, ArrayStrings.isAnagram1(s1, s2));
            Console.WriteLine("{0} and {1} IsAnagram1(): {2}", s2, s3, ArrayStrings.isAnagram1(s2, s3));
            Console.WriteLine("{0} and {1} IsAnagram1(): {2}", s3, s4, ArrayStrings.isAnagram1(s3, s4));
            Console.WriteLine("{0} and {1} IsAnagram1(): {2}", s1, s2, ArrayStrings.isAnagram2(s1, s2));
            Console.WriteLine("{0} and {1} IsAnagram1(): {2}", s2, s3, ArrayStrings.isAnagram2(s2, s3));
            Console.WriteLine("{0} and {1} IsAnagram1(): {2}", s3, s4, ArrayStrings.isAnagram2(s3, s4));*/

            String[] strs = input.Split(' ');
            String set = strs[0];
            int k = Convert.ToInt32(strs[1]);
            int count = 0;

            // Combinatorics.
            Combinatorics com = new Combinatorics();
            Console.WriteLine("nCk Combinations of {0}P{1}: {2} are:", set.Length, k, input );
            com.Init(set);
            count = com.StringCombinations(0, k);
            com.PrintSet();
            Console.WriteLine( "Count:{0} combinations when choosing {1} items from a set of {2}.",
                               count, k, set.Length);

            Console.WriteLine("nPk Permutations of {0}P{1}: {2} are:", set.Length, k, input);
            com.Init(set);
            count = com.StringPermutations(k);
            com.PrintSet();
            Console.WriteLine("Count:{0} Permutations when choosing {1} items from a set of {2}.",
                               count, k, set.Length);
        }
    }
}
