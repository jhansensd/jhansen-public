using System;
using algorithms.core.arraystrings;

namespace algorithms {
    public class Testing {
        public Testing() {
            Run();
        }

        void Run() {
            ArrayStringTests();
            ListTests();
            MiscTests();
        }

        void ArrayStringTests() {
            String input;
            do {
                input = Console.ReadLine();
                if (input.Length != 0) {
                    ArrayStringTesting.run(input);
                }
            } while (input.Length != 0);
            
            //ArrayStringTesting.run(null);
        }

        void ListTests() {

        }

        void MiscTests() {

        }
    }
}
