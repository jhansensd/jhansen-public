using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms.core.arraystrings {
    class Combinatorics {

        private StringBuilder output = new StringBuilder();
        bool[] found;
        private String input;
        private List<String> set;

        public void Init(String str) {
            input = str;
            found = new bool[str.Length];
            set = new List<String>();
        }

        public int StringPermutations(int k) {

            int count = 0;

            if ( output.Length == k ) {
                //System.out.print( out + " " );
                set.Add(output.ToString());
                return 1;
            }

            for (int i = 0; i < input.Length; i++ ) {
                if (found[i]) {
                    continue;
                }
                output.Append( input.ElementAt(i));
                found[i] = true;
                count += StringPermutations(k);
                found[i] = false;
                output.Remove(output.Length - 1, 1);
            }

            return count;
        }

        public int StringCombinations(int start, int k) {

            int count = 0;

            if ( output.Length == k ) {
                //System.out.print( out + " " );
                set.Add(output.ToString());
                return 1;
            }

            for (int i = start; i < input.Length; i++ ) {
                output.Append(input.ElementAt(i));
                count += StringCombinations(i + 1, k);
                output.Remove(output.Length - 1, 1);
            }

            return count;
        }

        public void PrintSet() {
            foreach(String s in set) {
                Console.Write(s + " ");
            }
            Console.WriteLine();
        }
    }
}
