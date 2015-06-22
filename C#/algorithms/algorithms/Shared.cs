using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms {
    class Shared {
        public static List<int> GrabArray(String input) {
            String[] strs = input.Split(' ');
            List<int> intList = new List<int>();
            for (int i = 0; i < strs.Length; i++) {
                intList.Add(Convert.ToInt32(strs[i]));
            }

            return intList;
        }
    }
}
