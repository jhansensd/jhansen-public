using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms.core.misc {
    class Bits {
        private int m_val;

        public Bits(int val) { m_val = val; }

        public void SetVal(int val) { m_val = val; }

        public void Print() {
            Console.WriteLine("Val is:" + m_val + " Binary is: " + Integer.toBinaryString(m_val));
        }

        public bool GetBit(int bit) { return (m_val & (1 << bit)) != 0; }
        public void SetBit(int bit) { m_val |= (1 << bit); }
        public void ClearBit(int bit) { m_val &= ~(1 << bit); }
        public void FlipBit(int bit) { m_val ^= (1 << bit); }

        public void UpdateBit(int bit, int on) {
            m_val &= ~(1 << bit); // Clear the bit we want.
            m_val |= (on << bit); // Or it with the shifted bit value.
        }

        public void ClearLowBits(int lowBit) {
            int mask = ~(-1 >> (31 - lowBit));
            m_val &= mask;
        }

        public void ClearHighBits(int highBit) {
            int mask = (1 << highBit) - 1;
            m_val &= mask;
        }

        public void ClearBitRange(int start, int end) {
            int range = (end - start) + 1;
            int mask = ~(~(-1 << range) << start);

            m_val &= mask; // Move the mask over start amount and mask.

            //Console.WriteLine("Temp mask is" + Integer.toBinaryString(mask));
        }

        public int CountOnBits() {
            int count = 0;

            int printVal = m_val;
            while (printVal != 0) {
                if ((printVal & 1) > 0) {
                    count++;
                }
                printVal = printVal >> 1;
            }

            return count;
        }
    }
}
