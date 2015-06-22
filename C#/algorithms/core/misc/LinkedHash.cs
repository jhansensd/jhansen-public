using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace algorithms.core.misc {
    class LinkedHash<Key, Value> {
        private int MAX_TABLE_SIZE;
        private LinkedHashEntry<Key, Value>[] table;

        public LinkedHash(int max) {
            MAX_TABLE_SIZE = max;
            table = new LinkedHashEntry<Key, Value>[MAX_TABLE_SIZE];
        }

        public void put(Key key, Value value) {

            int hash = key.GetHashCode() % MAX_TABLE_SIZE;

            if (table[hash] == null) {
                table[hash] = new LinkedHashEntry<Key, Value>(key, value);
                return;
            }

            foreach(LinkedHashEntry<Key, Value> entry in table[hash]) {
                if (entry.GetKey().Equals(key)) {
                    entry.SetValue(value);
                    return;
                }
            }

            table[hash].append(key, value);
        }

        public Value get(Key key) {
            int hash = key.GetHashCode() % MAX_TABLE_SIZE;

            if (table[hash] == null)
                return default(Value);

            LinkedHashEntry<Key, Value> collided = table[hash];

            foreach(LinkedHashEntry<Key, Value> entry in table[hash]) {
                if (entry.GetKey().Equals(key)) {
                    return collided.GetValue();
                }
            }

            return default(Value);
        }

        public void remove(Key key) {
            int hash = key.GetHashCode() % MAX_TABLE_SIZE;

            if (table[hash] == null)
                return;

            LinkedHashEntry<Key, Value> collided = table[hash];

            foreach(LinkedHashEntry<Key, Value> entry in table[hash]) {
                if (entry.GetKey().Equals(key)) {
                    if (collided.remove(key) == table[hash])
                        table[hash] = collided.Next;
                }
            }
        }

        public void print() {

            Console.WriteLine("Outputting hash table values.");
            LinkedHashEntry<Key, Value> entry = null;
            for (int i = 0; i < table.Length; i++) {
                if (table[i] == null)
                    continue;

                /*Iterator<LinkedHashEntry> iter = table[i].iterator();
                while (iter.hasNext()) {
                    entry = iter.next();
                    Console.WriteLine("Hash (Key:" + entry.Key + " Value:" + entry.Value + ")");
                }*/
            }
        }
    }
}
